package com.smart.desktop.common.widget.keyboard;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.smart.desktop.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kol(Fang Qiang) on 2017/8/2.
 * usage:
 */
public class NumKeyBoard extends FrameLayout {

    // 设置回调
    private Callback callback;

    public NumKeyBoard(@NonNull Context context) {
        super(context);
        init(context);
    }

    public NumKeyBoard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumKeyBoard(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.widget_numkeyboard, this);
        ButterKnife.bind(this, view);
    }

    @OnClick({R.id.keyboard_0, R.id.keyboard_1, R.id.keyboard_2, R.id.keyboard_3, R.id.keyboard_4,
            R.id.keyboard_5, R.id.keyboard_6, R.id.keyboard_7, R.id.keyboard_8, R.id.keyboard_9,
            R.id.keyboard_cancel, R.id.keyboard_backspace, R.id.keyboard_confirm})
    public void onClick(View view) {
        if (callback == null) return;
        switch (view.getId()) {
            case R.id.keyboard_0:
                callback.onKey(KEY_TYPE.KEY_0);
                break;
            case R.id.keyboard_1:
                callback.onKey(KEY_TYPE.KEY_1);
                break;
            case R.id.keyboard_2:
                callback.onKey(KEY_TYPE.KEY_2);
                break;
            case R.id.keyboard_3:
                callback.onKey(KEY_TYPE.KEY_3);
                break;
            case R.id.keyboard_4:
                callback.onKey(KEY_TYPE.KEY_4);
                break;
            case R.id.keyboard_5:
                callback.onKey(KEY_TYPE.KEY_5);
                break;
            case R.id.keyboard_6:
                callback.onKey(KEY_TYPE.KEY_6);
                break;
            case R.id.keyboard_7:
                callback.onKey(KEY_TYPE.KEY_7);
                break;
            case R.id.keyboard_8:
                callback.onKey(KEY_TYPE.KEY_8);
                break;
            case R.id.keyboard_9:
                callback.onKey(KEY_TYPE.KEY_9);
                break;
            case R.id.keyboard_cancel:
                callback.onKey(KEY_TYPE.KEY_CANCEL);
                break;
            case R.id.keyboard_backspace:
                callback.onKey(KEY_TYPE.KEY_BACKSPACE);
                break;
            case R.id.keyboard_confirm:
                callback.onKey(KEY_TYPE.KEY_CONFIRM);
                break;
            default:
                break;
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    // 按键回调
    public interface Callback {
        void onKey(KEY_TYPE keyType);
    }

    // 按键值枚举
    public enum KEY_TYPE {
        KEY_0("0"),
        KEY_1("1"),
        KEY_2("2"),
        KEY_3("3"),
        KEY_4("4"),
        KEY_5("5"),
        KEY_6("6"),
        KEY_7("7"),
        KEY_8("8"),
        KEY_9("9"),
        KEY_CANCEL(""),
        KEY_BACKSPACE(""),
        KEY_CONFIRM("");

        private String keyValue;

        KEY_TYPE(String keyValue) {
            this.keyValue = keyValue;
        }

        public String getKeyValue() {
            return keyValue;
        }
    }

}
