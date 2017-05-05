package com.kiss.readerlibrary.core.tree;

/**
 * @author qinghui
 * @date 2017/5/3
 */

public class TreeLeaf<P, E extends Brother> implements Leaf<P, E> {

    protected P p;
    protected E l;
    protected E r;

    public TreeLeaf(P p, E l, E r) {
        this.p = p;
        this.l = l;
        this.r = r;
        if (l != null)
            ((TreeLeaf) l).r = this;
        if (r != null)
            ((TreeLeaf) r).l = this;

    }

    @Override
    public P parent() {
        return p;
    }

    @Override
    public E left() {
        return l;
    }

    @Override
    public E right() {
        return r;
    }
}
