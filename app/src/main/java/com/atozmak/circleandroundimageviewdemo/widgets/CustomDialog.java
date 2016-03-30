package com.atozmak.circleandroundimageviewdemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.atozmak.circleandroundimageviewdemo.R;
import com.atozmak.circleandroundimageviewdemo.utils.LogUtil;
import com.atozmak.fisrtlibrarybyatozmak.Effectstype;
import com.atozmak.fisrtlibrarybyatozmak.NiftyDialogBuilder;

/**
 * Created by Mak on 2016/3/25.
 */
public class CustomDialog extends LinearLayout {
    public static final String TAG = LogUtil.makeLogTag(CustomDialog.class);

    private Effectstype effectstype;

    private Context mContext;


    public CustomDialog(Context context) {
        this(context, null);

    }

    public CustomDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    //-------------------------------------------

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        initFindViewById();

    }

    //-------------------------------------------

    public void dialogShow(View v) {
        NiftyDialogBuilder builder = NiftyDialogBuilder.getInstance(mContext);

        switch (v.getId()) {
            case R.id.btnFadeIn:
                effectstype = Effectstype.Fadein;
                break;
            case R.id.btnFall:
                effectstype = Effectstype.Fall;
                break;
            case R.id.btnFlipH:
                effectstype = Effectstype.Fliph;
                break;
            case R.id.btnFlipV:
                effectstype = Effectstype.Flipv;
                break;
            case R.id.btnNewsPaper:
                effectstype = Effectstype.Newspaper;
                break;
            case R.id.btnRotateBottom:
                effectstype = Effectstype.RotateBottom;
                break;

            case R.id.btnRotateLeft:
                effectstype = Effectstype.RotateLeft;
                // Log.v(TAG, ">>>>>>" + effectstype);
                //漏了写break导致执行不了。
                break;

            case R.id.btnShake:
                effectstype = Effectstype.Shake;
                break;
            case R.id.btnSideFall:
                effectstype = Effectstype.Sidefill;
                break;
            case R.id.btnSlideBottom:
                effectstype = Effectstype.Slidebottom;
                break;
            case R.id.btnSlideLeft:
                effectstype = Effectstype.Slideleft;
                break;
            case R.id.btnSlideRight:
                effectstype = Effectstype.Slideright;
                break;
            case R.id.btnSlideTop:
                effectstype = Effectstype.Slidetop;
                break;
            case R.id.btnSlit:
                effectstype = Effectstype.Slit;
                break;

        }

        builder
                .withTitle("hi this is title")
                .withTitleColor("#00ff00")
                .withMessage("hi this is message")
                .isCancelableOnTouhOutside(true)
                .withDuration(1000)
                .withEffect(effectstype)
                .withButton1Text("btn1")
                .withButton2Text("btn2")
                .setCustomView(R.layout.custome_view, v.getContext())
                .setButton1Click(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "OKLA", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButton2Click(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "NOLA", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }

    //-------------------------------------------
    private Button btnFadeIn, btnFall, btnFlipH, btnFlipV, btnNewsPaper,
            btnRotateBottom, btnRotateLeft, btnShake, btnSideFall,
            btnSlideBottom, btnSlideLeft, btnSlideRight, btnSlideTop, btnSlit;

    private void initFindViewById() {
        btnFadeIn = (Button) findViewById(R.id.btnFadeIn);
        btnFall = (Button) findViewById(R.id.btnFall);
        btnFlipH = (Button) findViewById(R.id.btnFlipH);
        btnFlipV = (Button) findViewById(R.id.btnFlipV);
        btnNewsPaper = (Button) findViewById(R.id.btnNewsPaper);
        btnRotateBottom = (Button) findViewById(R.id.btnRotateBottom);
        btnRotateLeft = (Button) findViewById(R.id.btnRotateLeft);
        btnShake = (Button) findViewById(R.id.btnShake);
        btnSideFall = (Button) findViewById(R.id.btnSideFall);
        btnSlideBottom = (Button) findViewById(R.id.btnSlideBottom);
        btnSlideLeft = (Button) findViewById(R.id.btnSlideLeft);
        btnSlideRight = (Button) findViewById(R.id.btnSlideRight);
        btnSlideTop = (Button) findViewById(R.id.btnSlideTop);
        btnSlit = (Button) findViewById(R.id.btnSlit);

        btnFadeIn.setOnClickListener(btnListener);
        btnFall.setOnClickListener(btnListener);
        btnFlipH.setOnClickListener(btnListener);
        btnFlipV.setOnClickListener(btnListener);
        btnNewsPaper.setOnClickListener(btnListener);
        btnRotateBottom.setOnClickListener(btnListener);
        btnRotateLeft.setOnClickListener(btnListener);
        btnShake.setOnClickListener(btnListener);
        btnSideFall.setOnClickListener(btnListener);
        btnSlideBottom.setOnClickListener(btnListener);
        btnSlideLeft.setOnClickListener(btnListener);
        btnSlideRight.setOnClickListener(btnListener);
        btnSlideTop.setOnClickListener(btnListener);
        btnSlit.setOnClickListener(btnListener);

    }

    private OnClickListener btnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogShow(v);
        }
    };

}
