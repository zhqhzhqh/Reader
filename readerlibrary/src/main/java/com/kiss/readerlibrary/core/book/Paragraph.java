package com.kiss.readerlibrary.core.book;

import com.kiss.readerlibrary.core.tree.TreeLeaf;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public class Paragraph extends TreeLeaf<Chapter, Paragraph> {
    public Paragraph(Chapter chapter, Paragraph l, Paragraph r) {
        super(chapter, l, r);
    }

    private String characters;

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }
}
