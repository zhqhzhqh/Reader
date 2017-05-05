package com.kiss.readerlibrary.core.book;

import android.os.Looper;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public class UrlChapter extends Chapter {

    private String url = null;

    public UrlChapter(Book book, Chapter l, Chapter r) {
        super(book, l, r);
    }

    @Override
    public void loadParagraphs() {
        if (Looper.getMainLooper() == Looper.myLooper())
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doLoadParagraphs();
                }
            });
        else
            doLoadParagraphs();
    }

    private void doLoadParagraphs() {
        try {
            if (TextUtils.isEmpty(url))
                return;
            URL url = new URL(this.url.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (200 == urlConnection.getResponseCode()) {
                //得到输入流
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                String content = baos.toString(p.bookInfo.charset);
                String[] lines = content.split("\n");

                for (int i = 0; i < lines.length; i++) {
                    String line = lines[i];
                    Paragraph paragraph = new Paragraph(this, this.getParagrahps().getChild(i - 1), this.getParagrahps().getChild(i + 1));
                    paragraph.setCharacters(line);
                    this.getParagrahps().addChild(paragraph);
                }

                loadPages();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
