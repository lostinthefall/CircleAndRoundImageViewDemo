package com.atozmak.circleandroundimageviewdemo.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.atozmak.circleandroundimageviewdemo.R;

/**
 * Created by Mak on 2016/3/27.
 */
public class ArcMenu extends ViewGroup implements View.OnClickListener {

    private static final int POS_LEFT_TOP = 0;
    private static final int POS_LEFT_BOTTOM = 1;
    private static final int POS_RIGHT_TOP = 2;
    private static final int POS_RIGHT_BOTTOM = 3;

    //这是什么？这是一个枚举类。
    private Position mPosition = Position.RIGHT_BOTTOM;
    private int mRadius;

    /**
     * 菜单的状态
     */
    private Status mCurrentStatus = Status.CLOSE;

    private View mCenterButton;

    private OnMenuItemClickListener mMenuItemClickListener;


    //------------------------------------------------------

    public enum Position {
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM
    }

    public enum Status {
        OPEN, CLOSE
    }

    //------------------------------------------------------

    /**
     * 点击子菜单的回调接口
     */
    public interface OnMenuItemClickListener {
        void onClick(View v, int pos);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.mMenuItemClickListener = listener;
    }

    //------------------------------------------------------

    public ArcMenu(Context context) {
        this(context, null);
    }

    public ArcMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    //------------------------------------------------------


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onClick(View v, int pos) {
                Toast.makeText(v.getContext(), "hihi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //其实传进来的这两个参数是什么。。。
    // 是[系统]根据[开发者]在[xml文件]中设置的
    // [宽高值（包括match_parent,wrap_content）]生成的[合成值]
    //是specMode和size的合成值
    //至于super.onMeasure为什么在那，不懂。。。（看下面）
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //直接调用ViewGroup中给我们提供好的measureChildren方法、
        //用下面这个方法就直接不用for循环了
        //measureChildren(widthMeasureSpec, heightMeasureSpec);
        //measureChildren 和 measureChild 不同。
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec)写在最后就
        //不用设置setMeasuredDimension，直接获得父类提供的默认值
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //@param changed This is a new size or position for this view
        if (changed) {
            layoutCenterButton();
            int count = getChildCount();
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                child.setVisibility(View.GONE);

                //cl: 其中一个子卫星菜单的x轴距离
                //ct：其中一个子卫星菜单的y轴距离
                int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
                int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));

                int cWidth = child.getMeasuredWidth();
                int cHeight = child.getMeasuredHeight();

                //如果菜单位置在左下，右下
                if (mPosition == Position.LEFT_BOTTOM || mPosition == Position.RIGHT_BOTTOM) {
                    ct = getMeasuredHeight() - cHeight - ct;
                }
                if (mPosition == Position.RIGHT_TOP || mPosition == Position.RIGHT_BOTTOM) {
                    cl = getMeasuredWidth() - cWidth - cl;
                }
                child.layout(cl, ct, cl + cWidth, ct + cHeight);
            }
        }
    }


    //------------------------------------------------------
    //------------------------------------------------------

    @Override
    public void onClick(View v) {
        rotateCBottom(v, 0f, 360f, 300);
        toggleMenu(300);
    }

    private void rotateCBottom(View v, float start, float end, int duration) {
        RotateAnimation animation = new RotateAnimation(
                start,
                end,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        animation.setDuration(duration);
        animation.setFillAfter(true);
        v.startAnimation(animation);
    }

    //为menuItem添加平移动画和旋转动画
    private void toggleMenu(int duration) {

        //count=主菜单+卫星菜单个数
        int count = getChildCount();

        for (int i = 0; i < count - 1; i++) {
            final View childView = getChildAt(i + 1);
            childView.setVisibility(View.VISIBLE);

            //end 0,0
            //start
            /**
             *  pi=180度
             *  x轴长度=半径*sin（角度），
             *  角度=pi/2/（卫星菜单个数-1）
             */
            int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
            int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));

            int xFlag = 1;
            int yFlag = 2;

            if (mPosition == Position.LEFT_TOP || mPosition == Position.LEFT_BOTTOM) {
                xFlag = -1;
            }
            if (mPosition == Position.LEFT_TOP || mPosition == Position.RIGHT_TOP) {
                yFlag = -1;
            }

            AnimationSet animationSet = new AnimationSet(true);
            TranslateAnimation tranAnim = null;

            //then open
            if (mCurrentStatus == Status.CLOSE) {
                tranAnim = new TranslateAnimation(xFlag * cl, 0, yFlag * ct, 0);
                childView.setClickable(true);
                childView.setFocusable(true);
            } else {
                //then close
                tranAnim = new TranslateAnimation(0, xFlag * cl, 0, yFlag * ct);
                childView.setClickable(false);
                childView.setFocusable(false);
            }
            tranAnim.setFillAfter(true);
            tranAnim.setDuration(duration);
            tranAnim.setStartOffset((i * 100) / count);

            tranAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (mCurrentStatus == Status.CLOSE) {
                        childView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            //旋转动画
            RotateAnimation rotateAnimation = new RotateAnimation(
                    0,
                    720,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
            );
            rotateAnimation.setDuration(duration);
            rotateAnimation.setFillAfter(true);

            animationSet.addAnimation(rotateAnimation);
            animationSet.addAnimation(tranAnim);
            childView.startAnimation(animationSet);

            final int pos = i + 1;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMenuItemClickListener != null) {
                        mMenuItemClickListener.onClick(childView, pos);
                    }
                    menuItemAnim(pos - 1);
                    changeStatus();
                }
            });
        }
    }

    private void changeStatus() {
        mCurrentStatus = (mCurrentStatus == Status.CLOSE ? Status.OPEN : Status.CLOSE);
    }

    private void menuItemAnim(int pos) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i + 1);
            if (i == pos) {
                childView.startAnimation(scaleBigAnim(300));
            } else {
                childView.startAnimation(scaleSmallAnim(300));
            }
            childView.setClickable(false);
            childView.setFocusable(false);
        }
    }

    private Animation scaleSmallAnim(int duration) {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnim = new ScaleAnimation(
                1.0f,
                0.0f,
                1.0f,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.0f);
        animationSet.addAnimation(scaleAnim);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(duration);
        animationSet.setFillAfter(true);
        return animationSet;
    }

    private Animation scaleBigAnim(int duration) {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f,
                4.0f,
                1.0f,
                4.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.0f);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(duration);
        animationSet.setFillAfter(true);
        return animationSet;
    }

    //------------------------------------------------------

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        //手动将100dp转换成pixel（因为android系统只认得pixel）
        mRadius = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                100,
                getResources().getDisplayMetrics()
        );

        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.ArcMenu,
                defStyleAttr,
                0
        );

        //把具体的数值传了进来
        int pos = a.getInt(R.styleable.ArcMenu_position, POS_RIGHT_BOTTOM);
        switch (pos) {
            case POS_LEFT_TOP:
                //mPosition是Position类型的（Position是枚举类）
                mPosition = Position.LEFT_TOP;
                break;
            case POS_LEFT_BOTTOM:
                mPosition = Position.LEFT_BOTTOM;
                break;
            case POS_RIGHT_TOP:
                mPosition = Position.RIGHT_TOP;
                break;
            case POS_RIGHT_BOTTOM:
                mPosition = Position.RIGHT_BOTTOM;
                break;
        }

        mRadius = (int) a.getDimension(
                R.styleable.ArcMenu_radius,
                TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        100,
                        getResources().getDisplayMetrics())
        );

        a.recycle();
    }

    private void layoutCenterButton() {
        mCenterButton = getChildAt(0);
        mCenterButton.setOnClickListener(this);

        //relativeWidth,relativeHeight 只是一个未经使用的原始值，都是表示一个长度值
        int relativeWidth = 0;
        int relativeHeight = 0;

        //这是mCenterButton的宽和高。
        int width = mCenterButton.getMeasuredWidth();
        int height = mCenterButton.getMeasuredHeight();

        switch (mPosition) {
            case LEFT_TOP:
                relativeWidth = 0;
                relativeHeight = 0;
                break;
            case LEFT_BOTTOM:
                relativeWidth = 0;
                //getMeasuredHeight()是viewGroup的高。
                relativeHeight = getMeasuredHeight() - height;
                break;
            case RIGHT_TOP:
                //getMeasuredHeight()是viewGroup的宽。
                relativeWidth = getMeasuredWidth() - width;
                relativeHeight = 0;
                break;
            case RIGHT_BOTTOM:
                relativeWidth = getMeasuredWidth() - width;
                relativeHeight = getMeasuredHeight() - height;
                break;
        }
        mCenterButton.layout(relativeWidth, relativeHeight, relativeWidth + width, relativeHeight + height);
    }

    public Status isOpen() {
        return mCurrentStatus = Status.OPEN;
    }
}
