package com.atozmak.circleandroundimageviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.atozmak.circleandroundimageviewdemo.utils.HandleEntryTheme;

/**
 * 参考自http://blog.csdn.net/lmj623565791/article/details/41967509
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandleEntryTheme.showAsecond(this);
        setTheme(R.style.AppTheme);
        //requestWindowFeature does not work.
        // supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }
}
