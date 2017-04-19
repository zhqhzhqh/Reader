package com.kiss.readerlibrary.storage;

/**
 * @author qinghui
 * @date 2017/4/12
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 书
 */
public final class Book {
    public List<Chapter> chapters = new ArrayList<>();
    public BookInfo bookInfo;

    @Override
    public String toString() {
        if (bookInfo != null) {
            if (bookInfo.name != null && !bookInfo.name.isEmpty())
                return bookInfo.name;
        }
        return super.toString();
    }
}
