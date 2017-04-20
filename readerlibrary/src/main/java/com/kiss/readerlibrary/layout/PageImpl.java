package com.kiss.readerlibrary.layout;

import android.graphics.Paint;
import android.text.TextPaint;

import com.kiss.readerlibrary.utils.PaintUtils;


/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public abstract class PageImpl {

    PageParameter pageParameter;

    float pageWidth;

    float pageHeight;

    float fontHeight;

    TextPaint textPaint;

    protected PageImpl(PageParameter pageParameter) {
        this.pageParameter = pageParameter;
        this.pageWidth = pageParameter.width - pageParameter.padding.left - pageParameter.padding.right;
        this.pageHeight = pageParameter.height - pageParameter.padding.top - pageParameter.padding.bottom;

        textPaint = PaintUtils.getTextPaint(pageParameter);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        this.fontHeight = fontMetrics.bottom - fontMetrics.top + fontMetrics.leading;
    }
}
