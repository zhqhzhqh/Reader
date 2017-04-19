package com.kiss.readerlibrary.layout.layouter;

import com.kiss.readerlibrary.layout.Line;
import com.kiss.readerlibrary.layout.Page;
import com.kiss.readerlibrary.storage.Paragraph;
import com.kiss.readerlibrary.storage.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangQinghui on 2017/4/18.
 */

final class PageLayouterTextImpl extends PageLayouterImpl {

    @Override
    List<Page> doLayout() {

        // 先分行
        List<Line> lines = new ArrayList<>();
        for (Paragraph paragraph : chapter.paragraphs) {

            String characters = paragraph.characters;
            int start = -1, end = characters.length();

            while (start + 1 < end) {
                Line line = new Line();

                Position startPosition = new Position();
                startPosition.chapter = chapter.index;
                startPosition.paragraph = paragraph.index;
                startPosition.character = start + 1;
                line.start = startPosition;

                start += textPaint.breakText(characters, start + 1, end, true, pageWidth, null);

                Position endPosition = new Position();
                endPosition.chapter = chapter.index;
                endPosition.paragraph = paragraph.index;
                endPosition.character = start;
                line.end = endPosition;
                if (start + 1 == end) {
                    line.isParagraghEnd = true;
                }

                lines.add(line);
            }
        }

        // 再分页
        List<Page> pages = new ArrayList<>();
        float height = 0;
        Page page = new Page();
        for (Line line : lines) {
            page.lines.add(line);
            height += fontHeight + pageParameter.lineSpacing + (line.isParagraghEnd ? pageParameter.paragraphSpacing : 0);
            if (height > pageHeight) {
                pages.add(page);
                page = new Page();
                page.lines.add(line);
                height = fontHeight + pageParameter.lineSpacing + (line.isParagraghEnd ? pageParameter.paragraphSpacing : 0);
            }
        }

        return pages;
    }
}
