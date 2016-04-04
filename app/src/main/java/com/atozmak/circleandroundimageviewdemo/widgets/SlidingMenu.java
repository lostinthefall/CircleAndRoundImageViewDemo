package com.atozmak.circleandroundimageviewdemo.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.atozmak.circleandroundimageviewdemo.R;
import com.atozmak.circleandroundimageviewdemo.utils.LogUtil;

/**
 * Created by Mak on 2016/3/29.
 */
public class SlidingMenu extends HorizontalScrollView {

    public static final String TAG = LogUtil.makeLogTag("SlidingMenu");

    private LinearLayout mWrapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;

    private int mMenuWidth;

    //menu右侧的宽度。dp
    private int mMenuRightPadding = 50;

    private boolean once = false;

    private boolean isOpen = false;

    private Button btnSwitch;

    //-----------------------------------------------------

    public SlidingMenu(Context context) {
        this(context, null);
    }

    //xml文件中有系统属性，但无自定义属性时，会调用此构造方法。
    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    //xml文件中使用了自定义属性时，调用此构造方法。
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        mScreenWidth = getResources().getDisplayMetrics().widthPixels;

/*        //把dp转为px
        mMenuRightPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                100,
                context.getResources().getDisplayMetrics()
        );*/

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyleAttr, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = a.getDimensionPixelSize(
                            attr,
                            (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    100,
                                    context.getResources().getDisplayMetrics()
                            )
                    );
                    break;
            }
        }


        a.recycle();

    }

    //-----------------------------------------------------

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!once) {
            mWrapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWrapper.getChildAt(0);
            mContent = (ViewGroup) mWrapper.getChildAt(1);

            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;

            once = true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //通过设置偏移量将menu隐藏。
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            //scrollTo移动的是该view的内容，
            // this在这里是自定义的horizontalScrollView，
            //即在onLayout的时候就把mMenu隐藏了。
            this.scrollTo(mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                //  getScrollX()  ：
                // The offset, in pixels, by which the content of this view is scrolled horizontally.
                int scrollX = getScrollX();

                // menu的左上角为（0，0），
                // getScrollX()是整个horizontal已经移动的距离，大小为menu的宽度，
                // 当用户手指开始滑动，getScrollX()获得新得值，
                // 如果getScrollX()比mMenuWidth/2要大，
                // 就是说用户在content界面向右滑动的距离小于mMenuWidth/2，
                // 那么显示content，把menu移回去。
                if (scrollX >= mMenuWidth / 2) {
                    //mMenuWidth为正，view的内容左移。
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        btnSwitch = (Button) findViewById(R.id.idBtn);
        btnSwitch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if (isOpen) return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (!isOpen) return;
        this.smoothScrollTo(mMenuWidth, 0);
        isOpen = false;
    }

    /**
     * 切换菜单
     */
    public void toggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    //添加了之后以下代码之后会使得menu有固定的效果。
    //只需要判断 l > oldl即为手指向右滑动
    //l是menu的0点与屏幕左边的距离
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        //因为l是已经移了mMenuWidth长度了，所以
        //变化 ：1 --> 0
        float scale = l * 1.0f / mMenuWidth;
        Log.v(TAG,"mMenuWidth="+mMenuWidth);
        Log.v(TAG,"l="+l+",oldl="+oldl);
        //              一开始已经 l=930,oldl=0， mMenuWidth=930
        //       当我手指落下的瞬间 l=911,oldl=930 ， l的值传给了oldl
        // 当我滑动了一大段距离之后 l=0,oldl=1

        // menu这个view是有自己的位置的，就是horizontalView中连着contentView的左边，
        // 按照规定，如果滑动contentView，menu也就会连着被滑出来，
        // 现在想要固定menu的话，就要使得在用户不断滑动的过程中，让menu都移到一个确定的位置，
        // 从视觉效果上来看的话，menu就固定着不动了。
        // 在用户想要滑出menu的一瞬间，menu已经向右移动了mMenuWidth*1的距离。
        // 用户几乎滑动了menu宽度这么长的距离的一瞬间，menu已经向右移动了mMenuWidth*0的距离。
        mMenu.setTranslationX(mMenuWidth * scale);
        //设置menu的缩放，透明度
        float leftScale = 1.0f - scale * 0.3f;
        float leftAlpha = 0.6f + 0.4f * (1 - scale);
        mMenu.setScaleX(leftScale);
        mMenu.setScaleY(leftScale);
        mMenu.setAlpha(leftAlpha);

        //设置content的缩放,中心点和大小。
        float rightScale = 0.7f + 0.3f * scale;
        mContent.setPivotX(0);
        mContent.setPivotY(mContent.getHeight() / 2);
        mContent.setScaleX(rightScale);
        mContent.setScaleY(rightScale);



    }
}
