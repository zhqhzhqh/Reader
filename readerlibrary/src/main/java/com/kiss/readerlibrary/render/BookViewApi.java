package com.kiss.readerlibrary.render;

import android.view.View;

import com.kiss.readerlibrary.layout.PageParameter;

/**
 * Created by ZhangQinghui on 2017/4/21.
 */

interface BookViewApi {

    View asView();

    BookViewApi asApi();

    PageParameter.SlideMode changeMode(PageParameter.SlideMode slideMode);
}
