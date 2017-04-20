package com.kiss.readerlibrary.layout;

import com.kiss.readerlibrary.storage.Chapter;

import java.util.List;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

abstract class PageLayouterImpl extends PageImpl {

    Chapter chapter;

    PageLayouterImpl(PageParameter pageParameter) {
        super(pageParameter);
    }


    List<Page> layout(Chapter chapter) {
        this.chapter = chapter;


        if ((this.chapter != null) && (this.pageParameter != null) && (this.pageWidth > 0) && (this.pageHeight > 0)) {
            return doLayout();
        } else {
            return null;
        }
    }

    abstract List<Page> doLayout();
}