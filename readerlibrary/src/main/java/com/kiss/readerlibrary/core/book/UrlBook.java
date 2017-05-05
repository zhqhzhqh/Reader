package com.kiss.readerlibrary.core.book;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public class UrlBook extends Book{

    public String catalogUrl;

    private String resolveFilePath;

    public List<CatalogItem> catalog = new ArrayList<>();

    @Override
    public void resolveCatalog() {
    }

    public class CatalogItem {
        public String chapterName;
        public String chapterUrl;
    }
}
