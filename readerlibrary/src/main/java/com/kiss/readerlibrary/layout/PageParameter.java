package com.kiss.readerlibrary.layout;

import android.graphics.Typeface;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

public final class PageParameter {

    public static class Padding {
        public float left;
        public float right;
        public float top;
        public float bottom;
    }

    public static class Font {
        public float fontSize;
        public Typeface typeface;
        public int color;
    }

    public Padding padding;

    public Font font;

    public float paragraphSpacing;

    public float lineSpacing;

}
