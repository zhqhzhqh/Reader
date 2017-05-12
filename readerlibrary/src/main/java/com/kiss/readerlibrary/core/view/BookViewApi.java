package com.kiss.readerlibrary.core.view;

import com.kiss.readerlibrary.core.common.BookInfo;
import com.kiss.readerlibrary.core.common.PageInfo;

/**
 * @author qinghui
 * @date 2017/5/12
 */

public interface BookViewApi {

    void loadLocakBook(final String filePath, final BookInfo bookInfo, final PageInfo pageInfo);

    void loadUrlBook(String catalogUrl, final BookInfo bookInfo, final PageInfo pageInfo);
}
