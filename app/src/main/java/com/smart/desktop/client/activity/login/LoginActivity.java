package com.smart.desktop.client.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.client.activity.user.UserActivity;
import com.smart.desktop.common.widget.TitleBuilder;
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
    private static final String IS_INTENT_2_USER = "isIntent2User";

    @BindView(R.id.et_operator_no)
    EditText userNo;
    @BindView(R.id.et_password)
    EditText password;

    private boolean isIntent2User;
    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            isIntent2User = bundle.getBoolean(IS_INTENT_2_USER);
        }

        new LoginPresenter(this, ApiRepository.getInstance());
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setLeftImage(R.drawable.arrow_icon)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setTitleText(getString(R.string.sign_in))
                .build();
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

    @OnClick({R.id.title_tv_left, R.id.btn_login})
    public void onViewClicked(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.title_tv_left:
                finish();
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
        if (isIntent2User){
            intent2Activity(UserActivity.class);
        }
        finish();
    }

    @Override
    public void clearEditText() {
        userNo.getText().clear();
        password.getText().clear();
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
