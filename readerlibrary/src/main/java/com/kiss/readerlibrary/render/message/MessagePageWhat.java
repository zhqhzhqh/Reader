package com.kiss.readerlibrary.render.message;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public enum MessagePageWhat {

    /****************************************/

    NULL_MESSAGE(),

    GO_TO_PAGE(Integer.class);

    /****************************************/

    Class dataType;

    MessagePageWhat(){}

    MessagePageWhat(Class dataType) {
        this.dataType = dataType;
    }
}
