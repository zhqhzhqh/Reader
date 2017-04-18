package com.kiss.readerlibrary.storage.loader;

import com.kiss.readerlibrary.storage.Book;

/**
 * Created by ZhangQinghui on 2017/4/17.
 */

abstract class BookLoaderImpl {

    abstract Book load(String path);
}
