package com.atozmak.circleandroundimageviewdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;

import com.atozmak.circleandroundimageviewdemo.utils.HandleEntryTheme;

/**
 * 参考自http://blog.csdn.net/lmj623565791/article/details/41967509
 */
public class MainActivity extends AppCompatActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandleEntryTheme.showAsecond(this);
        setTheme(R.style.AppTheme);
        //requestWindowFeature does not work.
        // supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("circleImage").setIndicator("圆形").setContent(R.id.firstTab));
        tabHost.addTab(tabHost.newTabSpec("scroll").setIndicator("滑动").setContent(R.id.secondTab));
        tabHost.addTab(tabHost.newTabSpec("dialog").setIndicator("自定义dialog").setContent(R.id.thirdTab));
        tabHost.addTab(tabHost.newTabSpec("starsView").setIndicator("卫星菜单").setContent(R.id.forthTab));

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.white));
        }

        tabHost.setCurrentTab(3);
    }
}
