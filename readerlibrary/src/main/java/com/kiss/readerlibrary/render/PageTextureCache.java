package com.kiss.readerlibrary.render;

import com.kiss.readerlibrary.utils.Cache;


/**
 * Created by ZhangQinghui on 2017/4/21.
 */

final class PageTextureCache extends Cache<PageTexture> {

    public void nextTexture() {
        next(this.p);
    }

    public void preTexture() {
        pre(this.n);
    }
}
