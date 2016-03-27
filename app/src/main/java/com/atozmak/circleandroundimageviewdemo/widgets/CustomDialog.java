package com.atozmak.circleandroundimageviewdemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.atozmak.circleandroundimageviewdemo.R;
import com.atozmak.fisrtlibrarybyatozmak.Effectstype;
import com.atozmak.fisrtlibrarybyatozmak.NiftyDialogBuilder;

/**
 * Created by Mak on 2016/3/25.
 */
public class CustomDialog extends LinearLayout {

    private Effectstype effectstype;
    private Button btn1, btn2;
    private Context mContext;


    public CustomDialog(Context context) {
        super(context);
        init(context);
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
            case R.id.btn1:
                effectstype = Effectstype.Fadein;
                break;
        }

        builder
                .withTitle("test dialog 1")
                .withTitleColor("#00ff00")
                .withMessage("hi this is mak")
                .isCancelableOnTouhOutside(true)
                .withDuration(1000)
                .withEffect(effectstype)
                .withButton1Text("yesla")
                .withButton2Text("nola")
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

    private void initFindViewById() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(btn1Listener);
        btn2.setOnClickListener(btn2Listener);
    }

    private OnClickListener btn1Listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogShow(v);
        }
    };
    private OnClickListener btn2Listener = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
