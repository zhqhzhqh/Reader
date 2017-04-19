package com.kiss.readerlibrary.storage.loader;

import com.kiss.readerlibrary.storage.Book;
import com.kiss.readerlibrary.storage.BookInfo;
import com.kiss.readerlibrary.storage.Chapter;
import com.kiss.readerlibrary.storage.Paragraph;

/**
 * Created by ZhangQinghui on 2017/4/17.
 */

final class BookLoaderTextImpl extends BookLoaderImpl {
    @Override
    Book load(String path) {
        return forge(path);
    }

    private Book forge(String path) {
        Book book = new Book();
        book.bookInfo = new BookInfo();
        book.bookInfo.path = path;
        book.bookInfo.auther = "天蚕土豆";
        book.bookInfo.name = "斗破苍穹";

        for (int i = 0; i < 10; i++) {
            Chapter chapter = new Chapter();
            chapter.name = "第" + (i + 1) + "章: " + "伪造的章节名";
            for (int j = 0; j < 10; j++) {
                Paragraph paragraph = new Paragraph();
                paragraph.characters = "第" + (j + 1) + "段: "
                        + "这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？等级制度：斗者，斗师，大斗师，斗灵，斗王，斗皇，斗宗，斗尊，斗圣，斗帝。";
                paragraph.index = j;
                chapter.paragraphs.add(paragraph);
            }
            chapter.index = i;
            book.chapters.add(chapter);
        }
        return book;
    }
}
