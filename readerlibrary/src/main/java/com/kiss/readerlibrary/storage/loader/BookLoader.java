package com.kiss.readerlibrary.storage.loader;

import com.kiss.readerlibrary.storage.Book;

/**
 * Created by ZhangQinghui on 2017/4/17.
 */

public class BookLoader {

    private static volatile BookLoader s;

    private static Book book;

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
        return bookLoader.load(path);
    }

    public Book getBook() {
        return book;

    }

    public void destoryBook() {
        book = null;

    }
}
