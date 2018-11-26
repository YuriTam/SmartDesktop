package com.smart.desktop.client.activity.user_add;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.common.constant.SysCode;
import com.smart.desktop.common.widget.TitleBuilder;
import com.smart.desktop.core.api.ApiRepository;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加操作员
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月26日
 */
public class UserAddActivity extends BaseActivity implements UserAddContract.View {

    @BindView(R.id.et_user_no)
    EditText etUserNo;
    @BindView(R.id.et_password)
    EditText etPassword;

    private UserAddContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new UserAddPresenter(this, ApiRepository.getInstance());
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_user_add;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setLeftImage(R.drawable.arrow_icon)
                .setTitleText(getString(R.string.add_user))
                .setRightText(getString(R.string.save))
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

    @OnClick({R.id.title_iv_left, R.id.title_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_left:
                finish();
                break;
            case R.id.title_tv_right:
                mPresenter.addUser(etUserNo.getText().toString(), etPassword.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void addSuccess() {
        setResult(SysCode.RESP_CODE);
        finish();
    }

    @Override
    public void showMsg(String errMsg) {
        showToast(errMsg);
    }

    @Override
    public void setPresenter(UserAddContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
