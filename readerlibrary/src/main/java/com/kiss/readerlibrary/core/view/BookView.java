package com.kiss.readerlibrary.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kiss.readerlibrary.core.book.Book;
import com.kiss.readerlibrary.core.book.Chapter;
import com.kiss.readerlibrary.core.book.LocalBook;
import com.kiss.readerlibrary.core.book.LocalChapter;
import com.kiss.readerlibrary.core.book.UrlBook;
import com.kiss.readerlibrary.core.book.UrlChapter;
import com.kiss.readerlibrary.core.common.BookInfo;
import com.kiss.readerlibrary.core.common.LoadChapterType;
import com.kiss.readerlibrary.core.common.MessageEvent;
import com.kiss.readerlibrary.core.common.PageInfo;
import com.kiss.readerlibrary.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author qinghui
 * @date 2017/5/5
 */

public class BookView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;

    private Book book;

    public BookView(Context context) {
        this(context, null);
    }

    public BookView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.holder = getHolder();
        this.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        EventBusUtils.register(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        EventBusUtils.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.what) {
            case RESOLVE_LOCAL_CATALOG_SUCCESS:
                loadLocalChapter();
                break;
            case RESOLVE_URL_CATALOG_SUCCESS:
                loadUrlChapter();
                break;
            case START_LOAD_CHAPTER_SUCCESS:
                if (event.data.equals(book.currentChapter)) {
                    doRender();
                }
                break;
        }
    }

    public void loadLocakBook(final String filePath, final BookInfo bookInfo, final PageInfo pageInfo) {
        // load book
        final LocalBook book = new LocalBook();
        this.book = book;
        book.bookInfo = bookInfo;
        book.pageInfo = pageInfo;
        book.originalFilePath = filePath;

        book.resolveCatalog();
    }

    public void loadUrlBook(String catalogUrl, final BookInfo bookInfo, final PageInfo pageInfo) {
        // load book
        final UrlBook book = new UrlBook();
        this.book = book;
        book.bookInfo = bookInfo;
        book.pageInfo = pageInfo;
        book.catalogUrl = catalogUrl;

        book.resolveCatalog();
    }

    private void loadLocalChapter() {
        for (int i = 0; i < ((LocalBook) book).catalog.size(); i++) {
            LocalChapter chapter = new LocalChapter(book, book.getChild(i - 1), book.getChild(i + 1));
            book.addChild(chapter);
        }

        book.loadChapter(book.getChild(book.pageInfo.pageContext.chapterId), LoadChapterType.START);
    }

    private void loadUrlChapter() {
        for (int i = 0; i < ((UrlBook) book).catalog.size(); i++) {
            Chapter chapter = new UrlChapter(book, book.getChild(i - 1), book.getChild(i + 1));
            book.addChild(chapter);
        }

        book.loadChapter(book.getChild(book.pageInfo.pageContext.chapterId), LoadChapterType.START);
    }

    private void doRender() {

    }
}
