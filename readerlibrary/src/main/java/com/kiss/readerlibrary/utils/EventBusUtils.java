package com.kiss.readerlibrary.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * @author qinghui
 * @date 2017/5/5
 */

public class EventBusUtils {

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }
}
