package com.atozmak.circleandroundimageviewdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Mak on 2016/3/21.
 */

public class HandleEntryTheme extends Handler {

    public static final int WAIT = 500;
    private final WeakReference<MainActivity> weakReference;

    public HandleEntryTheme(MainActivity mainActivity) {
        weakReference = new WeakReference<>(mainActivity);
    }

    @Override
    public void handleMessage(Message msg) {
        //   MainActivity mainActivity = weakReference.get();
        switch (msg.what) {
            case WAIT:
                try {
                    Thread.sleep(WAIT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public static void showAsecond(MainActivity mainActivity) {
        final HandleEntryTheme handler = new HandleEntryTheme(mainActivity);
        handler.sendEmptyMessage(WAIT);
    }
}

