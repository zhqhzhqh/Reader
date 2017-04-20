package com.kiss.readerlibrary.render;

import com.kiss.readerlibrary.utils.Singletonable;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public class PageRender implements Singletonable {

    private static final PageRender ourInstance = new PageRender();

    private PageRender() {
    }

    public static PageRender getInstance() {
        return ourInstance;
    }
}
