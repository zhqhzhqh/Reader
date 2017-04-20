package com.kiss.readerlibrary.render;

import android.view.View;

import com.kiss.readerlibrary.layout.PageImpl;
import com.kiss.readerlibrary.layout.PageParameter;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public abstract class PageView extends PageImpl {

    protected PageView(PageParameter pageParameter) {
        super(pageParameter);
    }

    public abstract PageApi asApi();

    public abstract View asView();
}
