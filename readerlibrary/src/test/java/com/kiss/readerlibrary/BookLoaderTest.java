package com.kiss.readerlibrary;

import com.kiss.readerlibrary.storage.Book;
import com.kiss.readerlibrary.storage.loader.BookLoader;

import org.junit.Test;

/**
 * Created by ZhangQinghui on 2017/4/17.
 */

public class BookLoaderTest {

    @Test
    public void testBookLoeder(){
        Book book = BookLoader.getInstance().initBook("xxx/xxx/xxx.txt");
        System.out.print(book);
    }
}
