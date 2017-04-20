package com.kiss.readerlibrary;

import com.kiss.readerlibrary.render.message.MessagePage;
import com.kiss.readerlibrary.utils.RxBus;

import org.junit.Test;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.kiss.readerlibrary.render.message.MessagePageWhat.GO_TO_PAGE;
import static com.kiss.readerlibrary.render.message.MessagePageWhat.NULL_MESSAGE;

/**
 * Created by ZhangQinghui on 2017/4/20.
 */

public class RxBusTest {

    @Test
    void testRxbusReceiver() {
        RxBus.getInstance().toObservable(MessagePage.class)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<MessagePage>() {
                    @Override
                    public void accept(MessagePage messagePage) throws Exception {
                        switch (messagePage.what) {
                            case NULL_MESSAGE:
                                break;
                            case GO_TO_PAGE: {
                                Integer pageNum = (Integer) messagePage.obj;
                            }
                            break;
                            default:
                                break;
                        }
                    }
                });
    }

    @Test
    public void testRxBusSend() {
        MessagePage nullMsg = new MessagePage(NULL_MESSAGE);
        RxBus.getInstance().post(nullMsg);
        MessagePage msg = new MessagePage(GO_TO_PAGE, Integer.valueOf(13));
        RxBus.getInstance().post(msg);
    }
}
