package com.kiss.readerlibrary.core.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;

import com.kiss.readerlibrary.core.book.Book;
import com.kiss.readerlibrary.core.book.Chapter;
import com.kiss.readerlibrary.core.book.Page;
import com.kiss.readerlibrary.core.common.LoadChapterType;
import com.kiss.readerlibrary.core.common.PageContext;
import com.kiss.readerlibrary.core.common.PageParameter;
import com.kiss.readerlibrary.core.common.PageTexture;
import com.kiss.readerlibrary.core.common.PageTextureCache;
import com.kiss.readerlibrary.utils.PaintUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qinghui
 * @date 2017/5/11
 */

public final class PageTextureManager implements PageTextureManagerApi {

    public PageTextureCache pageTextureCache = new PageTextureCache();

    private ExecutorService task = Executors.newSingleThreadScheduledExecutor();

    private Book book;

    private DrawUtil drawUtil;

    public PageTextureManager(Book book) {
        this.book = book;
        this.drawUtil = new DrawUtil(this.book.pageInfo.pageParameter);
        loadPageTexture();
    }

    @Override
    public void loadPageTexture() {
        final PageContext pageContext = book.pageInfo.pageContext;
        final Page currentPage = this.book.getChild(pageContext.chapterId).getPages().getChild(pageContext.pageId);
        drawUtil.drawBitmapTexture(currentPage, pageTextureCache.c);

        task.submit(new Runnable() {
            @Override
            public void run() {
                if (currentPage.right() != null) {
                    drawUtil.drawBitmapTexture(currentPage.right(), pageTextureCache.n);
                } else if (pageTextureCache.c.page.parent().right().getPages().getChild(0) != null) {
                    drawUtil.drawBitmapTexture(pageTextureCache.c.page.parent().right().getPages().getChild(0), pageTextureCache.n);
                } else {
                    book.loadChapter(pageTextureCache.c.page.parent().right(), LoadChapterType.PAGE_INIT);
                }

                if (currentPage.left() != null) {
                    drawUtil.drawBitmapTexture(currentPage.left(), pageTextureCache.p);
                } else if (pageTextureCache.c.page.parent().left().getPages().getChild(0) != null) {
                    drawUtil.drawBitmapTexture(pageTextureCache.c.page.parent().left().getPages().getChild(0), pageTextureCache.p);
                } else {
                    book.loadChapter(pageTextureCache.c.page.parent().left(), LoadChapterType.PAGE_INIT);
                }
            }
        });
    }

    public void onPageInitLoadChapterSucess(Chapter chapter) {
        if (chapter == pageTextureCache.c.page.parent().right()) {
            drawUtil.drawBitmapTexture(pageTextureCache.c.page.parent().right().getPages().getChild(0), pageTextureCache.n);
        } else if (chapter == pageTextureCache.c.page.parent().left()) {
            drawUtil.drawBitmapTexture(pageTextureCache.c.page.parent().left().getPages().getChild(0), pageTextureCache.p);
        }
    }

    public void onPageJumpLoadChapterSucess(Chapter chapter) {
        if (chapter == pageTextureCache.c.page.parent().right()) {
            drawUtil.drawBitmapTexture(pageTextureCache.c.page.parent().right().getPages().getChild(0), pageTextureCache.p);
            pageTextureCache.nextTexture();
        } else if (chapter == pageTextureCache.c.page.parent().left()) {
            drawUtil.drawBitmapTexture(pageTextureCache.c.page.parent().left().getPages().getChild(0), pageTextureCache.n);
            pageTextureCache.preTexture();
        }
    }

    // 加载下一页的缓存
    @Override
    public void nextPageTexture() {
        Page nextPage = null;
        if (pageTextureCache.n.page.right() != null) {
            nextPage = pageTextureCache.n.page.right();
        } else if (pageTextureCache.n.page.parent().right().getPages().getChild(0) != null) {
            nextPage = pageTextureCache.n.page.parent().right().getPages().getChild(0);
        } else {
            book.loadChapter(pageTextureCache.c.page.parent().right(), LoadChapterType.PAGE_JUMP);
        }
        if (nextPage != null) {
            drawUtil.drawBitmapTexture(nextPage, pageTextureCache.p);
            pageTextureCache.nextTexture();
        }
    }

    // 加载上一页的缓存
    @Override
    public void prePageTexture() {
        Page prePage = null;
        if (pageTextureCache.n.page.left() != null) {
            prePage = pageTextureCache.n.page.left();
        } else if (pageTextureCache.n.page.parent().left().getPages().getChild(0) != null) {
            prePage = pageTextureCache.n.page.parent().left().getPages().getChild(0);
        } else {
            book.loadChapter(pageTextureCache.c.page.parent().left(), LoadChapterType.PAGE_JUMP);
        }
        if (prePage != null) {
            drawUtil.drawBitmapTexture(prePage, pageTextureCache.n);
            pageTextureCache.preTexture();
        }
    }

    private class DrawUtil {

        private PageParameter pageParameter;

        private FontUtil textFontUtil = new FontUtil();

        private FontUtil titleFontUtil = new FontUtil();

        private FontUtil topFontUtil = new FontUtil();

        private FontUtil botttomFontUtil = new FontUtil();

        private class FontUtil {
            TextPaint paint;
            float height;
            float baseLine;
        }

        public DrawUtil(PageParameter pageParameter) {
            this.pageParameter = pageParameter;
        }

        private void updateParameter() {
            this.textFontUtil.paint = PaintUtils.getTextPaint();
            Paint.FontMetrics textFontMetrics = this.textFontUtil.paint.getFontMetrics();
            this.textFontUtil.baseLine = -textFontMetrics.top;
            this.textFontUtil.height = textFontMetrics.bottom - textFontMetrics.top + textFontMetrics.leading;

            this.titleFontUtil.paint = PaintUtils.getTitlePaint();
            Paint.FontMetrics titleFontMetrics = this.titleFontUtil.paint.getFontMetrics();
            this.titleFontUtil.baseLine = -titleFontMetrics.top;
            this.titleFontUtil.height = titleFontMetrics.bottom - titleFontMetrics.top + titleFontMetrics.leading;

            this.topFontUtil.paint = PaintUtils.getTopPaint();
            Paint.FontMetrics topFontMetrics = this.topFontUtil.paint.getFontMetrics();
            this.topFontUtil.baseLine = -topFontMetrics.top;
            this.topFontUtil.height = topFontMetrics.bottom - topFontMetrics.top + topFontMetrics.leading;

            this.botttomFontUtil.paint = PaintUtils.getBottomPaint();
            Paint.FontMetrics bottomFontMetrics = this.botttomFontUtil.paint.getFontMetrics();
            this.botttomFontUtil.baseLine = -bottomFontMetrics.top;
            this.botttomFontUtil.height = bottomFontMetrics.bottom - bottomFontMetrics.top + bottomFontMetrics.leading;
        }

        private void drawBitmapTexture(Page page, PageTexture pageTexture) {
            updateParameter();

            if (pageTexture == null) {
                PageTexture pt = new PageTexture();
                pt.bitmap = Bitmap.createBitmap((int) pageParameter.width, (int) pageParameter.height, Bitmap.Config.RGB_565);
                pt.canvas = new Canvas(pt.bitmap);
                pageTexture = pt;
            }

            pageTexture.page = page;
            Canvas canvas = pageTexture.canvas;
            drawBitmapTextureBackgroud(canvas);

            canvas.save();
            canvas.clipRect(new Rect((int) pageParameter.padding.left, (int) pageParameter.padding.top,
                    (int) (pageParameter.width - pageParameter.padding.right), (int) (pageParameter.padding.top + pageParameter.topBar.height)));
            canvas.translate((int) pageParameter.padding.left, (int) pageParameter.padding.top);
            drawBitmapTexturTop(canvas);
            canvas.restore();

            canvas.save();
            canvas.clipRect(new Rect((int) pageParameter.padding.left, (int) (pageParameter.padding.top + pageParameter.topBar.height),
                    (int) (pageParameter.width - pageParameter.padding.right), (int) (pageParameter.height - pageParameter.padding.bottom - pageParameter.bottomBar.height)));
            canvas.translate((int) pageParameter.padding.left, (int) (pageParameter.padding.top + pageParameter.topBar.height));
            drawBitmapTextureText(canvas);
            canvas.restore();

            canvas.save();
            canvas.clipRect(new Rect((int) pageParameter.padding.left, (int) (pageParameter.height - pageParameter.padding.bottom - pageParameter.bottomBar.height),
                    (int) (pageParameter.width - pageParameter.padding.right), (int) (pageParameter.height - pageParameter.padding.bottom)));
            canvas.translate((int) pageParameter.padding.left, (int) (pageParameter.height - pageParameter.padding.bottom - pageParameter.bottomBar.height));
            drawBitmapTexturBottom(canvas);
            canvas.restore();
        }

        private void drawBitmapTextureBackgroud(Canvas canvas) {
            canvas.drawColor(Color.GRAY);
        }

        private void drawBitmapTexturTop(Canvas canvas) {
            float fontHeight = topFontUtil.height;
            float fontBaseLine = topFontUtil.baseLine;
            TextPaint paint = topFontUtil.paint;

            canvas.drawColor(Color.RED);
            canvas.drawText("测试文案", pageParameter.width / 2, ((pageParameter.topBar.height - fontHeight) / 2) + fontBaseLine, paint);
        }

        private void drawBitmapTextureText(Canvas canvas) {
            float fontHeight = textFontUtil.height;
            float fontBaseLine = textFontUtil.baseLine;
            TextPaint paint = textFontUtil.paint;

            //XXX 画页面
            canvas.drawColor(Color.LTGRAY);
            canvas.drawText("测试文案", pageParameter.width / 2, fontBaseLine, paint);
        }

        private void drawBitmapTexturBottom(Canvas canvas) {
            float fontHeight = botttomFontUtil.height;
            float fontBaseLine = botttomFontUtil.baseLine;
            TextPaint paint = botttomFontUtil.paint;

            canvas.drawColor(Color.RED);
            canvas.drawText("测试文案", pageParameter.width / 2, ((pageParameter.bottomBar.height - fontHeight) / 2) + fontBaseLine, paint);
        }
    }
}
