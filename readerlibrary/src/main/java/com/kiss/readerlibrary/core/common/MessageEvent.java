package com.kiss.readerlibrary.core.common;

/**
 * @author qinghui
 * @date 2017/5/5
 */

public class MessageEvent {

    public What what;

    public Object data;

    public enum What {
        RESOLVE_LOCAL_CATALOG_SUCCESS,
        RESOLVE_URL_CATALOG_SUCCESS,
        START_LOAD_CHAPTER_SUCCESS,
        PAGE_LOAD_CHAPTER_SUCCESS
    }
}
