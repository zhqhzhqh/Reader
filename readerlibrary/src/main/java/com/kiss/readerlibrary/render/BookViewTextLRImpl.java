package com.kiss.readerlibrary.render;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.kiss.readerlibrary.layout.Page;
import com.kiss.readerlibrary.layout.PageLayouter;
import com.kiss.readerlibrary.storage.BookLoader;
import com.kiss.readerlibrary.storage.Chapter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZhangQinghui on 2017/4/21.
 */

final class BookViewTextLRImpl extends BookViewTextImpl {

    ExecutorService layoutService = Executors.newSingleThreadScheduledExecutor();

    ExecutorService renderService = Executors.newSingleThreadScheduledExecutor();

    PageCache pageCache = new PageCache();

    PageTextureCache pageTextureCache = new PageTextureCache();

    public BookViewTextLRImpl(Context context) {
        super(context);
    }

    @Override
    void doRender() {
        this.pageCache.p = null;

        Chapter curChapter = book.chapters.get(this.pageContext.start.chapter);
        PagesLoaderTask curChapterLoaderTask = new PagesLoaderTask(curChapter, new PagesLoaderCallback() {
            @Override
            public void onLoader(List<Page> pages) {
                BookViewTextLRImpl.this.pageCache.c = pages;

                pageTextureCache.p = null;

                // TODO: 2017/4/21  页面选择
                int startPageIndex = 0;

                PageRenderTask curPageRenderTask = new PageRenderTask(pages.get(startPageIndex), pageTextureCache.c,
                        new PageRenderTaskCallback() {
                            @Override
                            public void onRender(Bitmap bitmap) {
                                drawStatic(bitmap);
                            }
                        });
                renderService.submit(curPageRenderTask);

                //final PageRenderTask nextPageRenderTask = new PageRenderTask(pages.get(startPageIndex + 1), pageTextureCache.n,
                //        new PageRenderTaskCallback() {
                //            @Override
                //            public void onRender(Bitmap bitmap) {
                //                pageTextureCache.n = bitmap;
                //            }
                //        });
                //postDelayed(new Runnable() {
                //    @Override
                //    public void run() {
                //        renderService.submit(nextPageRenderTask);
                //    }
                //}, 1500);
            }
        });
        layoutService.submit(curChapterLoaderTask);

        Chapter nextChapter = BookLoader.getInstance().getBook().chapters.get(this.pageContext.start.chapter + 1);
        final PagesLoaderTask nextChapterLoaderTask = new PagesLoaderTask(nextChapter, new PagesLoaderCallback() {
            @Override
            public void onLoader(List<Page> pages) {
                BookViewTextLRImpl.this.pageCache.n = pages;
            }
        });
        postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutService.submit(nextChapterLoaderTask);
            }
        }, 1000);
    }

    /**********************************************************/
    private class PagesLoaderTask implements Runnable {

        private final Chapter chapter;

        private final PagesLoaderCallback callback;

        PagesLoaderTask(Chapter chapter, PagesLoaderCallback callback) {
            this.chapter = chapter;
            this.callback = callback;
        }

        @Override
        public void run() {
            List<Page> pages = PageLayouter.getInstance().layout(this.chapter, BookViewTextLRImpl.this.pageParameter);
            this.callback.onLoader(pages);
        }
    }

    interface PagesLoaderCallback {
        void onLoader(List<Page> pages);
    }

    /****************************************************************/

    private class PageRenderTask implements Runnable {

        private final Page page;
        private final PageTexture pageTexture;
        private final PageRenderTaskCallback callback;

        PageRenderTask(Page page, PageTexture pageTexture, PageRenderTaskCallback callback) {
            this.page = page;
            this.pageTexture = pageTexture;
            this.callback = callback;
        }

        @Override
        public void run() {
            callback.onRender(drawBitmapTexture(page, pageTexture));
        }
    }

    interface PageRenderTaskCallback {
        void onRender(Bitmap bitmap);
    }

    private Bitmap drawBitmapTexture(Page page, PageTexture pageTexture) {
        if (pageTexture == null) {
            PageTexture pt = new PageTexture();
            pt.bitmap = Bitmap.createBitmap((int) pageParameter.width, (int) pageParameter.height, Bitmap.Config.RGB_565);
            pt.canvas = new Canvas(pt.bitmap);
            pageTexture = pt;
        }
        drawBitmapTextureImpl(pageTexture.canvas);
        return pageTexture.bitmap;
    }

    private void drawBitmapTextureImpl(Canvas canvas){
        //XXX 画页面
        canvas.drawARGB(255, 192, 192, 192);
        canvas.drawText("测试画板", pageParameter.padding.left, pageParameter.padding.top + fontBaseLine, textPaint);
    }

    /****************************************************************/

    void drawStatic(Bitmap bitmap) {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawBitmap(bitmap, 0, 0, textPaint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null)
                holder.unlockCanvasAndPost(canvas);
        }
    }

    /****************************************************************/

}
