package com.kiss.readerlibrary.core.common;

/**
 * @author qinghui
 * @date 2017/5/5
 */

public class MessageEvent {

    public What what;

    public Object data;

    public enum What {
        RESOLVE_CATALOG_SUCCESS,
        CHAPTER_INIT_LOAD_SUCCESS,
        CHAPTER_PAGE_INIT_LOAD_SUCCESS,
        CHAPTER_PAGE_JUMP_LOAD_SUCCESS
    }
}
