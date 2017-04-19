package com.kiss.readerlibrary.layout;

import android.graphics.Typeface;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

public final class PageParameter {

    private static final PageParameter ourInstance = new PageParameter();

    private PageParameter() {
    }

    public static PageParameter getInstance() {
        return ourInstance;
    }

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

        public float fontSize;
        public Typeface typeface;

        public int color;
    }

    public Padding padding = new Padding();

    public Font font = new Font();

    public float paragraphSpacing;

    public float lineSpacing;

}
