package com.smart.desktop.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.desktop.R;

/**
 * 自定义组合控件
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年12月21日
 */
public class ArrowLinearLayout extends LinearLayout {
    private static final String TAG = ArrowLinearLayout.class.getSimpleName();

    //图标
    private ImageView iv_arrow_icon;
    //名称
    private TextView tv_arrow_name;

    public ArrowLinearLayout(Context context) {
        this(context,null);
    }

    public ArrowLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArrowLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
        //初始化自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArrowLinearLayout);
        if (typedArray != null){
            //图标
            Drawable drawable = typedArray.getDrawable(R.styleable.ArrowLinearLayout_icon_src);
            if (drawable != null){
                iv_arrow_icon.setImageDrawable(drawable);
                iv_arrow_icon.setVisibility(VISIBLE);
            }
            //名称
            String name = typedArray.getString(R.styleable.ArrowLinearLayout_arrow_name);
            if (!TextUtils.isEmpty(name)){
                tv_arrow_name.setText(name);
            }
            typedArray.recycle();
        }
    }

    private void initView(Context mContext){
        LayoutInflater.from(mContext).inflate(R.layout.arrow_line_layout,this,true);
        iv_arrow_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_arrow_name = (TextView) findViewById(R.id.tv_arrow_name);
    }

    public void setArrowIcon(Drawable drawable) {
        if (drawable != null){
            iv_arrow_icon.setImageDrawable(drawable);
            iv_arrow_icon.setVisibility(VISIBLE);
        }
    }

    public void setArrowIcon(int arrowResId) {
        if (arrowResId != 0){
            iv_arrow_icon.setImageResource(arrowResId);
            iv_arrow_icon.setVisibility(VISIBLE);
        }
    }

    public void setArrowName(String arrowName) {
        if (!TextUtils.isEmpty(arrowName)){
            tv_arrow_name.setText(arrowName);
        }
    }

    public void setArrowName(int arrowResId) {
        if (arrowResId != 0){
            tv_arrow_name.setText(arrowResId);
        }
    }

}
