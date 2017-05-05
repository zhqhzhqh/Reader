package com.kiss.readerlibrary.core.common;

import android.graphics.Typeface;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

public final class PageParameter {

    // 宽度
    public float width;

    // 高度
    public float height;

    // Padding
    public Padding padding = new Padding();

    // 顶部栏
    public Bar topBar;

    // 底部栏
    public Bar bottomBar;

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

    public static class Bar {

        public float height;

        Font font;

        public static Bar of(int height, Font font) {
            Bar bar = new Bar();
            bar.height = height;
            bar.font = font;
            return bar;
        }

        public static Bar of(Bar bar) {
            Bar newBar = new Bar();
            newBar.height = bar.height;
            newBar.font = bar.font;
            return newBar;
        }
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
