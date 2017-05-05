package com.kiss.readerlibrary.core.book;

import com.kiss.readerlibrary.core.common.LocalChapterSplitPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public class LocalBook extends Book {

    public String originalFilePath;

    private String resolveFilePath;

    public List<CatalogItem> catalog = new ArrayList<>();

    @Override
    public void resolveCatalog() {
    }

    public class CatalogItem {
        public String chapterName;
        public LocalChapterSplitPosition chapterPosition;
    }
}
