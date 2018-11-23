package com.smart.desktop.client.activity.splash;

import android.os.Bundle;

import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.common.widget.ReceiptView;
import com.smart.desktop.common.widget.TitleBuilder;

import butterknife.BindView;

/**
 * 启动界面
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月21日
 */
public class SplashActivity extends BaseActivity implements ReceiptView.OnRectClickListener {

    @BindView(R.id.rec_view)
    ReceiptView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        new TitleBuilder(this)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setTitleText("启动页")
                .build();
    }

    @Override
    protected void initEvent() {
        recView.setClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(ReceiptView.RecClick click) {
        if (click != null){
            showToast(click.name());
        }
    }
}
