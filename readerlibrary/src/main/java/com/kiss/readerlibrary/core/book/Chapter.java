package com.kiss.readerlibrary.core.book;

import android.graphics.Paint;
import android.text.TextPaint;

import com.kiss.readerlibrary.core.common.LoadChapterType;
import com.kiss.readerlibrary.core.common.MessageEvent;
import com.kiss.readerlibrary.core.common.PageParameter;
import com.kiss.readerlibrary.core.common.ParagraphPosition;
import com.kiss.readerlibrary.core.tree.TreeLeaf;
import com.kiss.readerlibrary.core.tree.TreeRoot;
import com.kiss.readerlibrary.utils.EventBusUtils;
import com.kiss.readerlibrary.utils.PaintUtils;

/**
 * @author qinghui
 * @date 2017/5/4
 */

public abstract class Chapter extends TreeLeaf<Book, Chapter> {

    public Chapter(Book book, Chapter l, Chapter r) {
        super(book, l, r);
    }

    private TreeRoot<Paragraph> paragrahps = new TreeRoot<>();

    private TreeRoot<Page> pages = new TreeRoot<>();

    public TreeRoot<Paragraph> getParagrahps() {
        return paragrahps;
    }

    public TreeRoot<Page> getPages() {
        return pages;
    }

    //
    private String name;

    private LoadChapterType loadChapterType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void loadChapter(LoadChapterType loadChapterType) {
        this.loadChapterType = loadChapterType;
        loadParagraphs();
    }

    public abstract void loadParagraphs();

    public void loadPages() {
        if (paragrahps.childSize() > 0) {
            doLoadPages();

            MessageEvent messageEvent = new MessageEvent();
            messageEvent.data = this;
            switch (loadChapterType) {
                case CHAPTER_INIT:
                    messageEvent.what = MessageEvent.What.CHAPTER_INIT_LOAD_SUCCESS;
                    break;
                case PAGE_INIT:
                    messageEvent.what = MessageEvent.What.CHAPTER_PAGE_INIT_LOAD_SUCCESS;
                    break;
                case PAGE_JUMP:
                    messageEvent.what = MessageEvent.What.CHAPTER_PAGE_JUMP_LOAD_SUCCESS;
                    break;
            }
            EventBusUtils.post(messageEvent);
        }
    }

    public void doLoadPages() {
        PageParameter pageParameter = p.pageInfo.pageParameter;
        TextPaint textPaint = PaintUtils.getTextPaint(pageParameter);
        float pageWidth = pageParameter.width - pageParameter.padding.left - pageParameter.padding.right;
        float pageHeight = pageParameter.height - pageParameter.padding.top - pageParameter.padding.bottom - pageParameter.topBar.height - pageParameter.bottomBar.height;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top + fontMetrics.leading;

        //// TODO: 2017/5/5 计算章节头高度
        int chapterHeiht = 100;

        int h = 0;
        h += chapterHeiht;
        Page page = new Page(this, pages.getChild(-1), pages.getChild(1));
        boolean isContextChapter = p.pageInfo.pageContext.chapterId == p.childIndexOf(this);
        for (int i = 0, l = 0, p = 0; i < paragrahps.childSize(); i++) {
            Paragraph paragraph = paragrahps.getChild(i);
            String characters = paragraph.getCharacters();
            if (isContextChapter && i == this.p.pageInfo.pageContext.paragraphId) {
                this.p.pageInfo.pageContext.pageId = p;
            }

            int start = -1, end = characters.length();
            while (start + 1 < end) {
                Line line = new Line(page, page.getChild(l - 1), page.getChild(l + 1));
                l++;
                ParagraphPosition paragraphPosition = new ParagraphPosition();
                paragraphPosition.paragraph = paragraph;
                paragraphPosition.start = start + 1;
                start += textPaint.breakText(characters, start + 1, end, true, pageWidth, null);
                paragraphPosition.end = start;
                line.paragraphPosition = paragraphPosition;
                page.addChild(line);

                h += fontHeight + pageParameter.lineSpacing + (line.paragraphPosition.end >= line.paragraphPosition.paragraph.getCharacters().length() ? pageParameter.paragraphSpacing : 0);
                if (h > pageHeight) {
                    p++;
                    Page newPage = new Page(this, page, pages.getChild(p + 1));
                    pages.addChild(page);
                    page = newPage;
                    h = 0;
                    l = 0;
                }
            }
        }
    }

    public void clear() {
        paragrahps.clearChild();
        pages.clearChild();
    }
}
