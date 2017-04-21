package com.kiss.readerlibrary.layout;

import android.graphics.Typeface;

import com.kiss.readerlibrary.utils.Singletonable;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

public final class PageParameter implements Singletonable {

    private static final PageParameter ourInstance = new PageParameter();

    private PageParameter() {
    }

    public static PageParameter getInstance() {
        return ourInstance;
    }

    // 宽度
    public float width;

    // 高度
    public float height;

    // Padding
    public Padding padding = new Padding();

    // 段间距
    public float paragraphSpacing;

    // 行间距
    public float lineSpacing;

    // 字体
    public Font font = new Font();

    // 滑动方式
    public SlideMode slideMode;

    /**************************************************/
    public static class Padding {

        Padding() {
        }

        public float left;
        public float right;

        public float top;
        public float bottom;

    }

    public static class Font {

        Font() {
        }

        public Typeface typeface;

        public float fontSize;

        public int color;

    }

    public enum SlideMode {
        SCROLL, SIDLE, EMULATE, NONE
    }
}
