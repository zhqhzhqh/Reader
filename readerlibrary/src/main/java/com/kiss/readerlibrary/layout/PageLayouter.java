package com.kiss.readerlibrary.layout;

import com.kiss.readerlibrary.storage.Chapter;
import com.kiss.readerlibrary.utils.Singletonable;

import java.util.List;

/**
 * Created by ZhangQinghui on 2017/4/19.
 */

public class PageLayouter implements Singletonable {
    private static final PageLayouter ourInstance = new PageLayouter();

    private PageLayouter() {
    }

    public static PageLayouter getInstance() {
        return ourInstance;
    }

    public List<Page> layout(Chapter chapter, PageParameter pageParameter) {
        PageLayouterImpl pageLayouter = new PageLayouterTextImpl(pageParameter);
        return pageLayouter.layout(chapter);
    }
}
