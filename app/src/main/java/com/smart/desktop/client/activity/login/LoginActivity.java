package com.smart.desktop.client.activity.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.client.activity.user.UserActivity;
import com.smart.desktop.core.api.ApiRepository;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月21日
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.user_no)
    TextInputEditText userNo;
    @BindView(R.id.password)
    TextInputEditText password;

    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new LoginPresenter(this, ApiRepository.getInstance());
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onStart();
    }

    @OnClick({R.id.tv_forgot_password, R.id.btn_login})
    public void onViewClicked(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.tv_forgot_password:
                showToast(getString(R.string.pls_contact_admin));
                break;
            case R.id.btn_login:
                mPresenter.login(userNo.getText().toString(), password.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess() {
        intent2Activity(UserActivity.class);
        finish();
    }

    @Override
    public void showMsg(String errMsg) {
        showToast(errMsg);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
