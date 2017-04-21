package com.kiss.readerlibrary.render;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.storage.Book;
import com.kiss.readerlibrary.utils.PaintUtils;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

abstract class BookViewImpl extends SurfaceView implements SurfaceHolder.Callback {

    protected SurfaceHolder holder;

    protected Book book;

    protected PageParameter pageParameter;

    protected PageContext pageContext;

    protected float fontHeight;

    protected TextPaint textPaint;

    public BookViewImpl(Context context) {
        super(context);
        this.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    void render(Book book, PageParameter pageParameter, PageContext pageContext) {
        this.book = book;
        this.pageParameter = pageParameter;
        this.pageContext = pageContext;

        this.pageParameter.width = this.getMeasuredWidth();
        this.pageParameter.height = this.getMeasuredHeight();

        this.textPaint = PaintUtils.getTextPaint(this.pageParameter);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        this.fontHeight = fontMetrics.bottom - fontMetrics.top + fontMetrics.leading;

        doRender();
    }

    abstract void doRender();
}
