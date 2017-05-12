package com.kiss.readerlibrary.core.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public class TreeRoot<C extends Parent> implements Root<C> {

    private final List<C> cs = new LinkedList<>();

    @Override
    public void addChild(C c) {
        cs.add(c);
    }

    @Override
    public void removeChild(C c) {
        cs.remove(c);
    }

    @Override
    public void clearChild() {
        cs.clear();
    }

    @Override
    public C getChild(int index) {
        if (index < 0 || index >= childSize())
            return null;
        return cs.get(index);
    }

    @Override
    public int childSize() {
        return cs.size();
    }

    @Override
    public int childIndexOf(C c) {
        return cs.indexOf(c);
    }
}
