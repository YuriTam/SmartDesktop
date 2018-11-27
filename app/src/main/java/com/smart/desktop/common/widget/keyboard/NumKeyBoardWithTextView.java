package com.smart.desktop.common.widget.keyboard;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.smart.desktop.common.utils.StringUtils;

/**
 * Created by Kol(Fang Qiang) on 2017/8/2.
 * usage:
 */
public class NumKeyBoardWithTextView extends NumKeyBoard implements NumKeyBoard.Callback {

    // 设置textView
    private TextView tv;
    private StringBuilder sb = new StringBuilder();
    // 设置小数位数
    private int floatCount = 0;
    // 设置限制长度
    private int maxLen = 12;
    // 设置最大值
    private long maxValue = 999999999999L;
    // 设置回调
    private Callback callback;

    public NumKeyBoardWithTextView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public NumKeyBoardWithTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumKeyBoardWithTextView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        super.setCallback(this);
    }

    public void setTextView(TextView tv) {
        this.tv = tv;
    }

    public void setFloat(int floatCount) {
        this.floatCount = floatCount;
        showInView();
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 获取输入结果
     *
     * @return 结果
     */
    public String getResult() {
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    @Override
    public void onKey(KEY_TYPE keyType) {
        if (tv != null) {
            switch (keyType) {
                case KEY_0:
                    //还没有输入的时候，不准直接输入0
                    if (sb.length() != 0) {
                        addChar("0");
                    }
                    break;
                case KEY_1:
                    addChar("1");
                    break;
                case KEY_2:
                    addChar("2");
                    break;
                case KEY_3:
                    addChar("3");
                    break;
                case KEY_4:
                    addChar("4");
                    break;
                case KEY_5:
                    addChar("5");
                    break;
                case KEY_6:
                    addChar("6");
                    break;
                case KEY_7:
                    addChar("7");
                    break;
                case KEY_8:
                    addChar("8");
                    break;
                case KEY_9:
                    addChar("9");
                    break;
                case KEY_BACKSPACE:
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    break;
            }
            showInView();
        }
        if (callback != null) {
            callback.onKey(keyType);
        }
    }

    // 添加字符
    private void addChar(String s) {
        if (sb.length() >= maxLen) {
            return;
        }
        long newValue = Long.parseLong(sb.append(s).toString());
        sb.deleteCharAt(sb.length() - 1);
        if (newValue > maxValue) {
            return;
        }
        sb.append(s);
    }

    // 显示到控件
    private void showInView() {
        tv.setText(StringUtils.formatAmount(sb.toString(), floatCount));
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
