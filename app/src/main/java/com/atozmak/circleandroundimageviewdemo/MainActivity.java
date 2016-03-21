package com.atozmak.circleandroundimageviewdemo;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandleEntryTheme.showAsecond(this);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

    }


}
