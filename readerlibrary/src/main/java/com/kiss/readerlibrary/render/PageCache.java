package com.kiss.readerlibrary.render;

import com.kiss.readerlibrary.layout.Page;
import com.kiss.readerlibrary.layout.PageLayouter;
import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.storage.BookLoader;

import java.util.List;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public class PageCache {

    public List<Page> preChapter;

    public List<Page> curChapter;

    public List<Page> nextChapter;

    /**
     * 预加载下一章
     *
     * @param chapter
     */
    public void next(int chapter) {
        List<Page> page = PageLayouter.
                getInstance().
                layout(BookLoader.getInstance().getBook().chapters.get(chapter), PageParameter.getInstance());
        if (page != null && page.size() > 0) {
            preChapter = curChapter;
            curChapter = nextChapter;
            nextChapter = page;
        }
    }

    /**
     * 预加载上一章
     *
     * @param chapter
     */
    public void pre(int chapter) {
        List<Page> page = PageLayouter.
                getInstance().
                layout(BookLoader.getInstance().getBook().chapters.get(chapter), PageParameter.getInstance());
        if (page != null && page.size() > 0) {
            nextChapter = curChapter;
            curChapter = preChapter;
            preChapter = page;
        }
    }
}
