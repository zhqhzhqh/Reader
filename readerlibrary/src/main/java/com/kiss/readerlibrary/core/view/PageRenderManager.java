package com.kiss.readerlibrary.core.view;

import com.kiss.readerlibrary.core.book.Book;

/**
 * @author qinghui
 * @date 2017/5/12
 */

public final class PageRenderManager {

    private final PageTextureManager pageTextureManager;
    private final Book book;

    public PageRenderManager(Book book, PageTextureManager pageTextureManager) {
        this.book = book;
        this.pageTextureManager = pageTextureManager;
    }
}
