package com.kiss.readerlibrary;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.kiss.readerlibrary.layout.Page;
import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.layout.PageLayouter;
import com.kiss.readerlibrary.storage.Book;
import com.kiss.readerlibrary.storage.Chapter;
import com.kiss.readerlibrary.storage.BookLoader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by ZhangQinghui on 2017/4/19.
 */

@RunWith(AndroidJUnit4.class)
public class LayoutTest {

    private PageParameter parameter;

    @Before
    public void testSetup() {
        parameter = PageParameter.getInstance();
        PageParameter.Padding padding = parameter.padding;
        padding.left = 1;
        padding.top = 1;
        padding.right = 1;
        padding.bottom = 1;
        parameter.lineSpacing = 30;
        parameter.paragraphSpacing = 50;
        PageParameter.Font font = parameter.font;
        font.color = Color.parseColor("#ffff00");
        font.fontSize = 30;
        font.typeface = Typeface.DEFAULT;
    }

    @Test
    public void testLayout() {
        Book book = BookLoader.getInstance().initBook("xxx/xxx/xxx.txt");

        Chapter chapter = book.chapters.get(0);
        List<Page> pages = PageLayouter.getInstance().layout(chapter, parameter, 720, 1280);

        Log.e("qinghui", pages.toString() + pages.size());
    }
}
