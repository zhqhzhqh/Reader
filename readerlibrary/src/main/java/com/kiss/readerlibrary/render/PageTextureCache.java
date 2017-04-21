package com.kiss.readerlibrary.render;

import android.graphics.Bitmap;

import com.kiss.readerlibrary.utils.Cache;


/**
 * Created by ZhangQinghui on 2017/4/21.
 */

final class PageTextureCache extends Cache<Bitmap> {

    public void nextTexture() {
        next(this.p);
    }

    public void preTexture() {
        pre(this.n);
    }
}
