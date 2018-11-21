package com.smart.desktop.client.activity.merchant;

import android.os.Bundle;
import android.view.View;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.common.widget.TitleBuilder;

import butterknife.OnClick;

/**
 * 商户信息
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年12月21日
 */
public class MerchantActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_merchant;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setLeftImage(R.drawable.arrow_icon)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setTitleText(getString(R.string.merchant_info))
                .build();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.title_iv_left})
    public void onClick(View view){
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()){
            case R.id.title_iv_left:
                finish();
                break;
            default:
                break;
        }
    }

}
