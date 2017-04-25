package com.kiss.readerlibrary.storage;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

public final class Position {

    public int chapter;

    public int paragraph;

    public int character;

    public Position() {
    }

    public Position(int chapter, int paragraph, int character) {
        this.chapter = chapter;
        this.paragraph = paragraph;
        this.character = character;
    }
}
