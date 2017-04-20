package com.kiss.readerlibrary.utils;

import com.kiss.readerlibrary.render.message.MessagePage;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author qinghui
 * @date 2017/4/1
 */

public class RxBus implements Singletonable {

    private static volatile RxBus singleton;

    private final Subject<Object> bus;

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (singleton == null) {
            synchronized (RxBus.class) {
                if (singleton == null) {
                    singleton = new RxBus();
                }
            }
        }
        return singleton;
    }

    /**
     * 发送消息
     *
     * @param message 发送的消息数据
     */
    public <T extends MessagePage> void post(T message) {
        bus.onNext(message);
    }

    /**
     * 收听消息
     *
     * @param <T>     消息数据类型
     * @param message 接受的消息数据
     */
    public <T extends MessagePage> Observable<T> toObservable(Class<T> message) {
        return bus.ofType(message);
    }
}
