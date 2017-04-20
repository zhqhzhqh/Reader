package com.kiss.readerlibrary.render.message;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public class MessagePage {

    public MessagePageWhat what;

    public Object obj;

    public MessagePage(MessagePageWhat what) {
        this(what, null);
    }

    public MessagePage(MessagePageWhat what, Object obj) {
        if (obj.getClass() != what.dataType) {
            throw new RuntimeException("obj->" + obj.getClass() + " not instance of class->" + what.dataType);
        }
        this.what = what;
        this.obj = obj;
    }
}
