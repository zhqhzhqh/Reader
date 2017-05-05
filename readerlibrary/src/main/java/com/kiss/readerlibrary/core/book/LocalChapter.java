package com.kiss.readerlibrary.core.book;

import android.text.TextUtils;

import com.kiss.readerlibrary.core.common.LocalChapterSplitPosition;

import java.io.RandomAccessFile;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public class LocalChapter extends Chapter {

    private LocalChapterSplitPosition position;

    public void setPosition(LocalChapterSplitPosition position) {
        this.position = position;
    }

    public LocalChapter(Book book, Chapter l, Chapter r) {
        super(book, l, r);
    }

    @Override
    public void loadParagraphs() {
        try {
            RandomAccessFile accessFile = new RandomAccessFile(((LocalBook) p).originalFilePath, "r");
            accessFile.seek(position.getStart());

            for (int readLength = 0; readLength < position.getLenght(); readLength++) {
                String line = accessFile.readLine();
                if (TextUtils.isEmpty(line)) {
                    line = "\n";
                }
                String tLine = new String(line.getBytes("ISO8859-1"), p.bookInfo.charset);

                Paragraph paragraph = new Paragraph(this, this.getParagrahps().getChild(readLength - 1), this.getParagrahps().getChild(readLength + 1));
                paragraph.setCharacters(tLine);
                this.getParagrahps().addChild(paragraph);
            }

            loadPages();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
