package com.kiss.readerlibrary.core.tree;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public interface Brother<E extends Brother> {

    E left();

    E right();
}
