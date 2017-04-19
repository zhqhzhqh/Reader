package com.kiss.readerlibrary;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextPaint;
import android.util.Log;

import com.kiss.readerlibrary.layout.PageParameter;
import com.kiss.readerlibrary.utils.PaintUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ZhangQinghui on 2017/4/19.
 */

@RunWith(AndroidJUnit4.class)
public class PaintLinebreakTest {

    private PageParameter parameter;

    @Before
    public void testSetup() {
        this.parameter = PageParameter.getInstance();
    }

    @Test
    public void testLinebreak() {
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

        TextPaint textPaint = PaintUtils.getTextPaint(parameter);

        String paragraph = "在异界中刘枫也需要经历无数腥风血雨的洗礼。但一个人的力量是单薄的。故事讲述刘枫来到异世界后已经小有成就，但前方的路会比他想像得更加艰难。是继续独闯还是营造势力。刘枫心思紧密的开始布置。他们踏上日不落要塞，将对这里的军团翻天覆地。在狼王的地盘上，体现了傲世本领。足以震撼您的视角。但刘枫能否找到菲儿？还有那些暗处的势力？刘枫能得到再次突破吗！";
        float[] widths = new float[5];

        int count = textPaint.breakText(paragraph, true, 1288, widths);

        Log.e("qinghui", count + "");
    }
}
