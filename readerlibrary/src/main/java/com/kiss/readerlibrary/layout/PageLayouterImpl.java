package com.kiss.readerlibrary.layout;

import android.graphics.Paint;
import android.graphics.Rect;
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

    List<Page> layout(Chapter chapter, PageParameter pageParameter, Rect size) {
        return this.layout(chapter, pageParameter, size.width(), size.height());
    }

    List<Page> layout(Chapter chapter, PageParameter pageParameter, float width, float height) {
        this.chapter = chapter;
        this.pageParameter = pageParameter;
        this.pageWidth = width - pageParameter.padding.left - pageParameter.padding.right;
        this.pageHeight = height - pageParameter.padding.top - pageParameter.padding.bottom;

        textPaint = PaintUtils.getTextPaint(pageParameter);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        this.fontHeight = fontMetrics.bottom - fontMetrics.top + fontMetrics.leading;

        if ((this.chapter != null) && (this.pageParameter != null) && (this.pageWidth > 0) && (this.pageHeight > 0)) {
            return doLayout();
        } else {
            return null;
        }
    }

    abstract List<Page> doLayout();
}