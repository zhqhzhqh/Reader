package com.kiss.readerlibrary.core.tree;

/**
 * @author qinghui
 * @date 2017/5/3
 */

public interface Node<P, E extends Brother, C> extends Parent<P>, Brother<E>, Child<C> {

}
