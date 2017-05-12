package com.kiss.readerlibrary.core.common;

import android.graphics.Color;
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

        public static Bar of(int height) {
            Bar bar = new Bar();
            bar.height = height;
            return bar;
        }

        public static Bar of(Bar bar) {
            Bar newBar = new Bar();
            newBar.height = bar.height;
            return newBar;
        }
    }

    // 字体
    public enum Font {

        TEXT(Typeface.DEFAULT, 32, Color.BLACK),
        TITLE(Typeface.DEFAULT, 32, Color.BLACK),
        TOP(Typeface.DEFAULT, 32, Color.BLACK),
        BOTTOM(Typeface.DEFAULT, 32, Color.BLACK);

        Font(Typeface typeface, float size, int color) {
            this.typeface = typeface;
            this.size = size;
            this.color = color;
        }

        public Typeface typeface;

        public float size;

        public int color;

        public void valueOf(Font font) {
            this.typeface = font.typeface;
            this.size = font.size;
            this.color = font.color;
        }
    }

    public enum SlideMode {
        SCROLL, SIDLE, EMULATE, NONE
    }
}
