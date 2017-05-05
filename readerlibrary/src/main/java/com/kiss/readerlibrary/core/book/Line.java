package com.kiss.readerlibrary.core.book;

import com.kiss.readerlibrary.core.common.ParagraphPosition;
import com.kiss.readerlibrary.core.tree.TreeLeaf;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public class Line extends TreeLeaf<Page, Line> {

    public Line(Page page, Line l, Line r) {
        super(page, l, r);
    }

    public ParagraphPosition paragraphPosition;
}
