package com.kiss.readerlibrary.utils;

/**
 * Created by ZhangQinghui on 2017/4/21.
 */

public abstract class Cache<T> {
    public T p;
    public T c;
    public T n;

    protected void next(T n) {
        this.p = this.c;
        this.c = this.n;
        this.n = n;
    }

    protected void pre(T p) {
        this.n = this.c;
        this.c = this.p;
        this.p = p;
    }
}
