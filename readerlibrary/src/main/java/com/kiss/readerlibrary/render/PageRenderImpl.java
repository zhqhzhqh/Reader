package com.kiss.readerlibrary.render;

import com.kiss.readerlibrary.layout.PageImpl;
import com.kiss.readerlibrary.layout.PageParameter;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

abstract class PageRenderImpl extends PageImpl {

    PageView pageView;

    PageRenderImpl(PageParameter pageParameter) {
        super(pageParameter);
    }

    PageView render(PageCache pageCache) {

        doSetPageView();

        return doRender(pageCache);
    }

    abstract void doSetPageView();

    abstract PageView doRender(PageCache pageCache);
}
