package com.smart.desktop.client.activity.setting.base_setting;

import android.os.Bundle;

import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.core.api.ApiRepository;

/**
 * 设置界面基类
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月27日
 */
public abstract class BaseSettingActivity extends BaseActivity implements BaseSettingContract.View {

    protected BaseSettingContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new BaseSettingPresenter(this, ApiRepository.getInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onStart();
    }

    @Override
    public void saveSuccess() {
        finish();
    }

    @Override
    public void showMsg(String errMsg) {
        showToast(errMsg);
    }

    @Override
    public void setPresenter(BaseSettingContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
