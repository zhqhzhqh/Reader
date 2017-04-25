package com.kiss.reader;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.render.BookRender;
import com.kiss.readerlibrary.render.BookView;
import com.kiss.readerlibrary.storage.Book;
import com.kiss.readerlibrary.storage.BookLoader;

/**
 * Created by ZhangQinghui on 2017/4/25.
 */

public final class BookTestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_test);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }, 1000);
    }

    private void init() {
        ViewGroup view = (ViewGroup) findViewById(R.id.view_book);
        PageParameter parameter = PageParameter.getInstance();
        PageParameter.Padding padding = parameter.padding;
        padding.left = 61;
        padding.top = 6;
        padding.right = 6;
        padding.bottom = 6;
        parameter.width = view.getWidth();
        parameter.height = view.getHeight();
        parameter.lineSpacing = 30;
        parameter.paragraphSpacing = 50;
        PageParameter.Font font = parameter.font;
        font.color = Color.parseColor("#ff0000");
        font.fontSize = 30;
        font.typeface = Typeface.DEFAULT;
        parameter.slideMode = PageParameter.SlideMode.SIDLE;

        Book book = BookLoader.getInstance().initBook("xxx/xxx/xxx.txt");
        BookView bookView = BookRender.getInstance().render(this, book, parameter);

        view.addView(bookView.asView());
        ((TextView)findViewById(R.id.status)).setText(book.bookInfo.name);
    }

}
