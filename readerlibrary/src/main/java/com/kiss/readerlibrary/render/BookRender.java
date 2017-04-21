package com.kiss.readerlibrary.render;

import android.app.Activity;

import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.storage.Book;
import com.kiss.readerlibrary.utils.Singletonable;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public class BookRender implements Singletonable {

    private static final BookRender ourInstance = new BookRender();

    private BookRender() {
    }

    public static BookRender getInstance() {
        return ourInstance;
    }

    public BookView render(Activity context, Book book, PageParameter pageParameter) {
        return new BookView().render(context, book, pageParameter);
    }
}
