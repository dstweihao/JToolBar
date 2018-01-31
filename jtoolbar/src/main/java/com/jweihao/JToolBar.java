package com.jweihao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wh.jtoolbar.R;

/**
 * Created by weihao on 2018/1/30.
 */

public class JToolBar extends RelativeLayout {


    private Drawable mLeftBackground;
    private int mLeftTextColor;
    private String mLeftText;
    private Drawable mRightBackground;
    private int mRightTextColor;
    private String mRightText;
    private float mTitleSize;
    private int mTitleTextColor;
    private String mTitle;
    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;
    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;
    private jToolBarClickListener mListener;
    public static final int LEFT_BUTTON = 0;
    public static final int RIGHT_BUTTON = 1;
    private Drawable mTitleBackground;


    public JToolBar(Context context) {
        super(context);
    }

    public JToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public JToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @SuppressLint("ResourceAsColor")
    private void initView(Context context, AttributeSet attrs) {
        //通过这个方法，将你在attrs.xml中定义的declare-styleable的所有属性的值
        //存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.JToolBar);
        //从TypedArray中取出对应的值来为要设置的属性赋值
        //左边按钮属性获取
        mLeftBackground = ta.getDrawable(R.styleable.JToolBar_leftBackground);
        mLeftTextColor = ta.getColor(R.styleable.JToolBar_leftTextColor,  R.color.black);
        mLeftText = ta.getString(R.styleable.JToolBar_leftText);
        //右边按钮属性获取
        mRightBackground = ta.getDrawable(R.styleable.JToolBar_rightBackground);
        mRightTextColor = ta.getColor(R.styleable.JToolBar_rightTextColor,  R.color.black);
        mRightText = ta.getString(R.styleable.JToolBar_rightText);
        //标题属性获取
        mTitleSize = ta.getDimension(R.styleable.JToolBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.JToolBar_titleTextColor, R.color.black);
        mTitle = ta.getString(R.styleable.JToolBar_title);
        //获取完TypeArray的值后，一般都要调用recyle方法避免重新创建的时候的错误。
        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        //为创建的组件元素赋值
        //值来源于我们在引用的xml文件中给对应属性的赋值
        //左边按钮属性设置
        mLeftButton.setTextColor(mLeftTextColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mLeftButton.setBackground(mLeftBackground);
        }
        mLeftButton.setText(mLeftText);

        //右边按钮属性设置
        mRightButton.setTextColor(mRightTextColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mRightButton.setBackground(mRightBackground);
        }
        mRightButton.setText(mRightText);

        //标题栏属性设置
        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleSize);



        //为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //为该控件设置在RelativeLayout中的位置
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        //添加到ViewGroup，添加一个带有指定布局参数的子视图。
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);

        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);




        //按钮的点击事件，不需要具体的实现
        //只需调用接口的方法，回调的时候，会有具体的实现
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });


    }

    //暴露一个方法给调用者来注册接口回调
    //通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(jToolBarClickListener mListener) {
        this.mListener = mListener;
    }


    //设置按钮的显示与否通过id区分按钮，flag区分是否显示
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == LEFT_BUTTON) {
                mLeftButton.setVisibility(View.VISIBLE);
            } else {
                mRightButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == LEFT_BUTTON) {
                mLeftButton.setVisibility(View.GONE);
            } else {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }


    //接口对象，实现回到机制，在回调方法中
    //通过映射的接口对象调用接口中的方法
    //不用去考虑如何实现，具体的实现由调用者去创建
    public interface jToolBarClickListener {

        //左按钮点击事件
        void leftClick();

        //右按钮点击事件
        void rightClick();


    }


}
