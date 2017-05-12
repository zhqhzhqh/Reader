package com.kiss.readerlibrary.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kiss.readerlibrary.core.book.Book;
import com.kiss.readerlibrary.core.book.Chapter;
import com.kiss.readerlibrary.core.book.LocalBook;
import com.kiss.readerlibrary.core.book.LocalChapter;
import com.kiss.readerlibrary.core.book.UrlBook;
import com.kiss.readerlibrary.core.common.BookInfo;
import com.kiss.readerlibrary.core.common.LoadChapterType;
import com.kiss.readerlibrary.core.common.MessageEvent;
import com.kiss.readerlibrary.core.common.PageInfo;
import com.kiss.readerlibrary.core.common.PositionXY;
import com.kiss.readerlibrary.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author qinghui
 * @date 2017/5/5
 */

public class BookView extends SurfaceView implements SurfaceHolder.Callback, BookViewApi {

    private SurfaceHolder holder;

    private Book book;

    private PageTextureManager pageTexureManager;

    private EventGestureRecognition eventGestureRecognition = new EventGestureRecognition();

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
        if (book != null) {
            book.pageInfo.pageParameter.width = this.getWidth();
            book.pageInfo.pageParameter.height = this.getHeight();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (book != null) {
            book.pageInfo.pageParameter.width = width;
            book.pageInfo.pageParameter.height = height;
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        EventBusUtils.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.what) {
            case RESOLVE_CATALOG_SUCCESS:
                loadChapter();
                break;
            case CHAPTER_INIT_LOAD_SUCCESS:
                doRender();
                break;
            case CHAPTER_PAGE_INIT_LOAD_SUCCESS:
                this.pageTexureManager.onPageInitLoadChapterSucess((Chapter) event.data);
            case CHAPTER_PAGE_JUMP_LOAD_SUCCESS:
                this.pageTexureManager.onPageJumpLoadChapterSucess((Chapter) event.data);
                break;
        }
    }

    @Override
    public void loadLocakBook(final String filePath, final BookInfo bookInfo, final PageInfo pageInfo) {
        // load book
        final LocalBook book = new LocalBook();
        this.book = book;
        book.bookInfo = bookInfo;
        book.pageInfo = pageInfo;
        book.originalFilePath = filePath;

        book.resolveCatalog();
    }

    @Override
    public void loadUrlBook(String catalogUrl, final BookInfo bookInfo, final PageInfo pageInfo) {
        // load book
        final UrlBook book = new UrlBook();
        this.book = book;
        book.bookInfo = bookInfo;
        book.pageInfo = pageInfo;
        book.catalogUrl = catalogUrl;

        book.resolveCatalog();
    }

    private void loadChapter() {
        int size = (book instanceof LocalBook) ? ((LocalBook) book).catalog.size() : ((UrlBook) book).catalog.size();
        for (int i = 0; i < size; i++) {
            LocalChapter chapter = new LocalChapter(book, book.getChild(i - 1), book.getChild(i + 1));
            book.addChild(chapter);
        }

        book.loadChapter(book.getChild(book.pageInfo.pageContext.chapterId), LoadChapterType.CHAPTER_INIT);
    }

    private void doRender() {
        this.pageTexureManager = new PageTextureManager(this.book);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return eventGestureRecognition.onTouchEvent(event);
    }

    public class EventGestureRecognition {

        private PositionXY downPosition = new PositionXY();

        private long downTime;

        private boolean isMoveAction = false;

        // TODO: 2017/5/12 pixtutils
        private float moveDistanceX = 35;
        private float moveDistanceY = 35;

        private float upCount = 0;

        private EventDispatcher eventDispatcher = new EventDispatcher();

        private Runnable downRunnable = new Runnable() {
            @Override
            public void run() {
                if (0 == upCount) {
                    eventDispatcher.onLongClick(downPosition);
                }
            }
        };
        private Runnable upRunnable = new Runnable() {
            @Override
            public void run() {
                if (upCount > 1) {
                    eventDispatcher.onDoubleClick(downPosition);
                } else {
                    eventDispatcher.onClick(downPosition);
                }
            }
        };

        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downTime = System.currentTimeMillis();
                    downPosition.x = event.getX();
                    downPosition.y = event.getY();
                    if (!isMoveAction) {
                        postDelayed(downRunnable, 500);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    upCount++;
                    if (System.currentTimeMillis() - downTime < 500 && !isMoveAction) {
                        removeCallbacks(downRunnable);
                        removeCallbacks(upRunnable);
                        postDelayed(upRunnable, 500);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!isMoveAction && (Math.abs(event.getX() - downPosition.x) > moveDistanceX ||
                            Math.abs(event.getY() - downPosition.y) > moveDistanceY)) {
                        isMoveAction = true;
                        removeCallbacks(downRunnable);
                        removeCallbacks(upRunnable);
                    }
                    if (isMoveAction) {
                        PositionXY movePostion = new PositionXY();
                        movePostion.x = event.getX();
                        movePostion.y = event.getY();
                        eventDispatcher.onMoveClick(movePostion);
                    }
                    break;
            }
            return true;
        }
    }

    public class EventDispatcher {

        // 单击
        public void onClick(PositionXY positionXY) {
        }

        // 双击
        public void onDoubleClick(PositionXY positionXY) {

        }

        // 长按
        public void onLongClick(PositionXY positionXY) {
        }

        // 滑动
        public void onMoveClick(PositionXY positionXY) {

        }
    }
}
