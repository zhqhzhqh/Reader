package com.kiss.readerlibrary.utils;

import android.graphics.Paint;
import android.text.TextPaint;

import com.kiss.readerlibrary.core.common.PageParameter;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

public class PaintUtils {

    public static TextPaint getTextPaint() {
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(PageParameter.Font.TEXT.size);
        paint.setTypeface(PageParameter.Font.TEXT.typeface);
        paint.setColor(PageParameter.Font.TEXT.color);
        return paint;
    }

    public static TextPaint getTitlePaint() {
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(PageParameter.Font.TITLE.size);
        paint.setTypeface(PageParameter.Font.TITLE.typeface);
        paint.setColor(PageParameter.Font.TITLE.color);
        return paint;
    }

    public static TextPaint getTopPaint() {
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(PageParameter.Font.TOP.size);
        paint.setTypeface(PageParameter.Font.TOP.typeface);
        paint.setColor(PageParameter.Font.TOP.color);
        return paint;
    }

    public static TextPaint getBottomPaint() {
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(PageParameter.Font.BOTTOM.size);
        paint.setTypeface(PageParameter.Font.BOTTOM.typeface);
        paint.setColor(PageParameter.Font.BOTTOM.color);
        return paint;
    }
}
