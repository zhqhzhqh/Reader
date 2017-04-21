package com.kiss.readerlibrary.layout;

import android.graphics.Paint;
import android.text.TextPaint;

import com.kiss.readerlibrary.storage.Chapter;
import com.kiss.readerlibrary.utils.PaintUtils;

import java.util.List;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

abstract class PageLayouterImpl {

    Chapter chapter;

    PageParameter pageParameter;

    float pageWidth;

    float pageHeight;

    float fontHeight;

    TextPaint textPaint;

    PageLayouterImpl(PageParameter pageParameter) {
        this.pageParameter = pageParameter;
        this.pageWidth = this.pageParameter.width - this.pageParameter.padding.left - this.pageParameter.padding.right;
        this.pageHeight = this.pageParameter.height - this.pageParameter.padding.top - this.pageParameter.padding.bottom;
        this.textPaint = PaintUtils.getTextPaint(pageParameter);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        this.fontHeight = fontMetrics.bottom - fontMetrics.top + fontMetrics.leading;
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