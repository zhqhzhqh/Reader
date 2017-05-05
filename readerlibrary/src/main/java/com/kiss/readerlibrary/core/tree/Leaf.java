package com.kiss.readerlibrary.core.tree;

/**
 * @author qinghui
 * @date 2017/5/3
 */

public interface Leaf<P, E extends Brother> extends Parent<P>, Brother<E> {
}
