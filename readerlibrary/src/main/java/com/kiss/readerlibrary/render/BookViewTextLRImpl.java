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

        Chapter curChapter = BookLoader.getInstance().getBook().chapters.get(this.pageContext.start.chapter);
        PagesLoaderTask curChapterLoaderToask = new PagesLoaderTask(curChapter, new PagesLoaderCallback() {
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

                final PageRenderTask nextPageRenderTask = new PageRenderTask(pages.get(startPageIndex + 1), pageTextureCache.n,
                        new PageRenderTaskCallback() {
                            @Override
                            public void onRender(Bitmap bitmap) {
                                pageTextureCache.n = bitmap;
                            }
                        });
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        renderService.submit(nextPageRenderTask);
                    }
                }, 500);
            }
        });
        layoutService.submit(curChapterLoaderToask);

        Chapter nextChapter = BookLoader.getInstance().getBook().chapters.get(this.pageContext.start.chapter + 1);
        final PagesLoaderTask nextChapterLoaderToask = new PagesLoaderTask(nextChapter, new PagesLoaderCallback() {
            @Override
            public void onLoader(List<Page> pages) {
                BookViewTextLRImpl.this.pageCache.n = pages;
            }
        });
        postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutService.submit(nextChapterLoaderToask);
            }
        }, 1000);
    }

    /**********************************************************/
    class PagesLoaderTask implements Runnable {

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

    class PageRenderTask implements Runnable {

        private final Page page;
        private final Bitmap bitmap;
        private final PageRenderTaskCallback callback;

        public PageRenderTask(Page page, Bitmap bitmap, PageRenderTaskCallback callback) {
            this.page = page;
            this.bitmap = bitmap;
            this.callback = callback;
        }

        @Override
        public void run() {
            callback.onRender(drawBitmapTexture(page, bitmap));
        }
    }

    interface PageRenderTaskCallback {
        void onRender(Bitmap bitmap);
    }

    private Bitmap drawBitmapTexture(Page page, Bitmap bitmap) {
        if (bitmap == null)
            bitmap = Bitmap.createBitmap((int) pageParameter.width, (int) pageParameter.height, Bitmap.Config.RGB_565);

        Canvas canvas = holder.lockCanvas();
        try {
            canvas.drawARGB(120, 255, 255, 255);
            canvas.drawText("测试画板", pageParameter.padding.left, pageParameter.padding.top, textPaint);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            holder.unlockCanvasAndPost(canvas);
        }
        return null;
    }

    /****************************************************************/

    void drawStatic(Bitmap bitmap) {
        Canvas canvas = holder.lockCanvas();
        try {
            canvas.drawBitmap(bitmap, 0, 0, textPaint);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            holder.unlockCanvasAndPost(canvas);
        }
    }

    /****************************************************************/

}
