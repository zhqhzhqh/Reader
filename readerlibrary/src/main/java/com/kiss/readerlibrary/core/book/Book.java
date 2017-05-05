package com.kiss.readerlibrary.core.book;

import com.kiss.readerlibrary.core.common.BookInfo;
import com.kiss.readerlibrary.core.common.LoadChapterType;
import com.kiss.readerlibrary.core.common.PageInfo;
import com.kiss.readerlibrary.core.tree.TreeRoot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public abstract class Book extends TreeRoot<Chapter> {

    private ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public Chapter currentChapter;

    public BookInfo bookInfo;

    public PageInfo pageInfo;


    // 解析章节目录
    public abstract void resolveCatalog();

    // 加载章节
    public void loadChapter(Chapter chapter, final LoadChapterType loadChapterType) {

        Chapter oldChapter = currentChapter;
        currentChapter = chapter;

        if (oldChapter != null && !checkUse(oldChapter))
            oldChapter.clear();
        if (oldChapter.left() != null && !checkUse(oldChapter.left()))
            oldChapter.left().clear();
        if (oldChapter.right() != null && !checkUse(oldChapter.right()))
            oldChapter.right().clear();

        if (currentChapter.getParagrahps().childSize() <= 0)
            currentChapter.loadChapter(loadChapterType);
        if (currentChapter.left().getParagrahps().childSize() <= 0)
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    currentChapter.left().loadChapter(loadChapterType);
                }
            });
        if (currentChapter.right().getParagrahps().childSize() <= 0)
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    currentChapter.right().loadChapter(loadChapterType);
                }
            });
    }

    private boolean checkUse(Chapter oldChapter) {
        return oldChapter.equals(currentChapter) || oldChapter.equals(currentChapter.left()) || oldChapter.equals(currentChapter.right());
    }
}
