package com.kiss.readerlibrary.render;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.storage.Book;
import com.kiss.readerlibrary.storage.Position;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public class BookView implements BookViewApi {

    private BookViewImpl bookViewImpl;

    private PageParameter pageParameter;

    private Activity context;

    private Book book;

    BookView render(Activity context, Book book, PageParameter pageParameter) {
        this.context = context;
        this.book = book;
        this.pageParameter = pageParameter;
        bookViewImpl = this.pageParameter.slideMode == PageParameter.SlideMode.SCROLL ?
                new BookViewTextScrollImpl(this.context) : new BookViewTextLRImpl(this.context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        bookViewImpl.setLayoutParams(layoutParams);
        PageContext pageContext = new PageContext();
        pageContext.start = new Position(0, 0, 0);
        bookViewImpl.torRender(this.book, this.pageParameter, pageContext);
        return this;
    }

    @Override
    public View asView() {
        return bookViewImpl;
    }

    @Override
    public BookViewApi asApi() {
        return this;
    }

    @Override
    public PageParameter.SlideMode changeMode(PageParameter.SlideMode slideMode) {
        return null;
    }
}
