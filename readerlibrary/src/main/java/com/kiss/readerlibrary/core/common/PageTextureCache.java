package com.kiss.readerlibrary.core.common;

/**
 * Created by ZhangQinghui on 2017/4/21.
 */

final public class PageTextureCache extends Cache<PageTexture> {


    public void nextTexture() {
        next(this.p);
    }

    public void preTexture() {
        pre(this.n);
    }
}
