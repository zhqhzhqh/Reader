package com.kiss.readerlibrary.render.message;

import android.view.View;

import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.render.PageApi;
import com.kiss.readerlibrary.render.PageView;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public final class ScrollPageView extends PageView {

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
