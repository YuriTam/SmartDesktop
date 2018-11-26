package com.smart.desktop.client.activity.user_edit;

import android.content.Context;
import android.content.Intent;
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
 * 编辑用户
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月26号
 */
public class UserEditActivity extends BaseActivity implements UserEditContract.View {
    private static final String USER_NO = "mUserNo";

    @BindView(R.id.et_user_no)
    EditText etUserNo;
    @BindView(R.id.et_password_one)
    EditText etPasswordOne;
    @BindView(R.id.et_password_two)
    EditText etPasswordTwo;

    private UserEditContract.Presenter mPresenter;
    private String mUserNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            mUserNo = getIntent().getStringExtra(USER_NO);
        }
        new UserEditPresenter(this, ApiRepository.getInstance(), mUserNo);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_user_edit;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setLeftImage(R.drawable.arrow_icon)
                .setTitleText(getString(R.string.edit_user))
                .setRightText(getString(R.string.save))
                .build();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        etUserNo.setText(mUserNo);
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
                mPresenter.editUser(etPasswordOne.getText().toString(), etPasswordTwo.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void onEditSuccess() {
        setResult(SysCode.RESP_CODE);
        finish();
    }

    @Override
    public void showMsg(String errMsg) {
        showToast(errMsg);
    }

    @Override
    public void setPresenter(UserEditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static Intent newInstance(Context context, String userNo) {
        Intent intent = new Intent(context, UserEditActivity.class);
        intent.putExtra(USER_NO, userNo);
        return intent;
    }
}
