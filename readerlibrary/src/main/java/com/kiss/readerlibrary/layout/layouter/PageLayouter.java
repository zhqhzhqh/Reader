package com.kiss.readerlibrary.layout.layouter;

import com.kiss.readerlibrary.layout.Page;
import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.storage.Chapter;

import java.util.List;

/**
 * Created by ZhangQinghui on 2017/4/19.
 */

public class PageLayouter {
    private static final PageLayouter ourInstance = new PageLayouter();

    private PageLayouter() {
    }

    public static PageLayouter getInstance() {
        return ourInstance;
    }

    public List<Page> layout(Chapter chapter, PageParameter pageParameter, float width, float height) {
        PageLayouterImpl pageLayouter = new PageLayouterTextImpl();
        return pageLayouter.layout(chapter, pageParameter, width, height);
    }
}
