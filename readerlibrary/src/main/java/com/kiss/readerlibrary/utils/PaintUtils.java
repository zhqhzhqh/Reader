package com.kiss.readerlibrary.utils;

import android.graphics.Paint;
import android.text.TextPaint;

import com.kiss.readerlibrary.core.common.PageParameter;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

public class PaintUtils {

    public static TextPaint getTextPaint(PageParameter pageParameter) {
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(pageParameter.font.fontSize);
        paint.setTypeface(pageParameter.font.typeface);
        paint.setColor(pageParameter.font.color);
        return paint;
    }
}
