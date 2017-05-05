package com.kiss.readerlibrary.core.book;

import com.kiss.readerlibrary.core.tree.TreeNode;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public class Page extends TreeNode<Chapter, Page, Line> {

    public Page(Chapter chapter, Page l, Page r) {
        super(chapter, l, r);
    }
}
