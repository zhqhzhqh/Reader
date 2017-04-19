package com.kiss.readerlibrary.storage;

/**
 * @author qinghui
 * @date 2017/4/12
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 章
 */
public final class Chapter extends Indexable {
    public List<Paragraph> paragraphs = new ArrayList<>();
    public String name;
}
