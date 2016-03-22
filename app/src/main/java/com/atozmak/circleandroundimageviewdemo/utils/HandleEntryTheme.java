package com.atozmak.circleandroundimageviewdemo.utils;

import android.os.Handler;
import android.os.Message;

import com.atozmak.circleandroundimageviewdemo.MainActivity;

import java.lang.ref.WeakReference;

/**
 *
 */

public class HandleEntryTheme extends Handler {

    public static final int WAIT = 300;
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

