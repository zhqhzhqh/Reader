package com.kiss.readerlibrary.render;

import android.view.View;

import com.kiss.readerlibrary.layout.PageParameter;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public class ScrollPageView extends PageView {

    protected ScrollPageView(PageParameter pageParameter) {
        super(pageParameter);
    }

    @Override
    public PageApi asApi() {
        return null;
    }

    @Override
    public View asView() {
        return null;
    }
}
