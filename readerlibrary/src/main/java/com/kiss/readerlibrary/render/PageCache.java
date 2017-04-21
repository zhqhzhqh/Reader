package com.kiss.readerlibrary.render;

import com.kiss.readerlibrary.layout.Page;
import com.kiss.readerlibrary.layout.PageLayouter;
import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.storage.BookLoader;
import com.kiss.readerlibrary.utils.Cache;

import java.util.List;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

final class PageCache extends Cache<List<Page>> {

    /**
     * 预加载下一章
     *
     * @param chapter
     */
    public boolean nextChapter(int chapter) {
        List<Page> page = PageLayouter.
                getInstance().
                layout(BookLoader.getInstance().getBook().chapters.get(chapter), PageParameter.getInstance());
        if (page != null && page.size() > 0) {
            next(page);
            return true;
        }
        return false;
    }

    /**
     * 预加载上一章
     *
     * @param chapter
     */
    public boolean preChapter(int chapter) {
        List<Page> page = PageLayouter.
                getInstance().
                layout(BookLoader.getInstance().getBook().chapters.get(chapter), PageParameter.getInstance());
        if (page != null && page.size() > 0) {
            pre(page);
            return true;
        }
        return false;
    }
}
