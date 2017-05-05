package com.kiss.readerlibrary.core.tree;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public interface Child<C extends Parent> {

    void addChild(C c);

    void removeChild(C c);

    void clearChild();

    int childSize();

    C getChild(int index);
}
