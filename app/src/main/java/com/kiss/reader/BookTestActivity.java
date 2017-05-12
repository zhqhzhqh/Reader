package com.kiss.reader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;

import com.kiss.readerlibrary.core.common.PageParameter;
import com.kiss.readerlibrary.core.view.BookView;

/**
 * Created by ZhangQinghui on 2017/4/25.
 */

public final class BookTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_test);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }, 50);
    }

    private void init() {
        ViewGroup view = (ViewGroup) findViewById(R.id.view_book);
        PageParameter parameter = new PageParameter();
        PageParameter.Padding padding = parameter.padding;
        padding.left = 30;
        padding.top = 30;
        padding.right = 30;
        padding.bottom = 30;
        parameter.width = view.getWidth();
        parameter.height = view.getHeight();
        parameter.lineSpacing = 30;
        parameter.paragraphSpacing = 50;
        parameter.topBar = PageParameter.Bar.of(96);
        parameter.bottomBar = PageParameter.Bar.of(parameter.topBar);
        parameter.slideMode = PageParameter.SlideMode.SIDLE;

        BookView bookView = (BookView) findViewById(R.id.view_book);
        bookView.loadLocakBook(null, null, null);
    }
}
