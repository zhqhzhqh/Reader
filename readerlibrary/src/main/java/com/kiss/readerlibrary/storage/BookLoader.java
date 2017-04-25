package com.kiss.readerlibrary.storage;

import com.kiss.readerlibrary.utils.Singletonable;

/**
 * Created by ZhangQinghui on 2017/4/17.
 */

public final class BookLoader implements Singletonable {

    private static volatile BookLoader s;

    private Book book;

    private BookLoader() {
    }

    public static BookLoader getInstance() {
        if (s == null) {
            synchronized (BookLoader.class) {
                if (s == null)
                    s = new BookLoader();
            }
        }
        return s;
    }

    public Book initBook(String path) {
        if (book != null) {
            if (book.bookInfo.path.equalsIgnoreCase(path)) {
                return book;
            } else {
                throw new RuntimeException("you should call destoryBook() first !");
            }
        }

        BookLoaderImpl bookLoader = new BookLoaderTextImpl();
        book = bookLoader.load(path);
        return book;
    }

    public Book getBook() {
        return book;

    }

    public void destoryBook() {
        book = null;
    }
}
