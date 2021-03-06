package com.smart.desktop.client.activity.main;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.client.activity.cash_register.CashRegisterActivity;
import com.smart.desktop.client.activity.login.LoginActivity;
import com.smart.desktop.client.activity.merchant.MerchantActivity;
import com.smart.desktop.client.activity.setting.SettingActivity;
import com.smart.desktop.client.activity.trans_list.TransListActivity;
import com.smart.desktop.client.activity.user_list.UserListActivity;
import com.smart.desktop.client.activity.version.VersionActivity;
import com.smart.desktop.client.fragment.EditDialogFragment;
import com.smart.desktop.common.widget.ReceiptView;
import com.smart.desktop.common.widget.TitleBuilder;
import com.smart.desktop.core.api.ApiRepository;
import com.stx.xhb.xbanner.XBanner;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月20日
 */
public class MainActivity extends BaseActivity implements XBanner.XBannerAdapter, MainContract.View, ReceiptView.OnRectClickListener {
    private static final String IS_INTENT_2_USER = "isIntent2User";
    private Integer[] mImages = {R.mipmap.ad_point, R.mipmap.ad_quick, R.mipmap.ad_union};

    @BindView(R.id.rec_view)
    ReceiptView mRecView;
    @BindView(R.id.banner)
    XBanner mBanner;

    private EditDialogFragment mEditDialogFragment;
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置为主界面
        setMainActivity(true);

        new MainPresenter(this, ApiRepository.getInstance());
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        new TitleBuilder(this)
                .setRightImage(R.drawable.user_icon)
                .setTitleText(getString(R.string.main_title))
                .build();
    }

    @Override
    protected void initEvent() {
        mRecView.setClickListener(this);
    }

    @Override
    protected void initData() {
        mBanner.setData(Arrays.asList(mImages), null);
        mBanner.setmAdapter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        ((ImageView) view).setImageResource(mImages[position]);
    }

    @Override
    public void onClick(ReceiptView.RecClick click) {
        if (UIUtils.isDoubleClick()) return;
        switch (click){
            case CENTER:
                intent2Activity(CashRegisterActivity.class);
                break;
            case TOP_LEFT:
                intent2Activity(LoginActivity.class);
                break;
            case TOP_RIGHT:
                showToast("其他");
                break;
            case BOTTOM_LEFT:
                showToast("管理");
                break;
            case BOTTOM_RIGHT:
                showToast("打印");
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.title_iv_right, R.id.btn_merchant, R.id.btn_trans_list,
            R.id.btn_sys_setting, R.id.btn_version})
    public void onViewClicked(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.title_iv_right:
                //跳转到用户界面
                mPresenter.intent2UserInfo();
                break;
            case R.id.btn_merchant:
                intent2Activity(MerchantActivity.class);
                break;
            case R.id.btn_trans_list:
                intent2Activity(TransListActivity.class);
                break;
            case R.id.btn_sys_setting:
                showInputAdminPasswordDialog();
                break;
            case R.id.btn_version:
                intent2Activity(VersionActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * 提示输入管理员密码弹出层
     */
    private void showInputAdminPasswordDialog() {
        mEditDialogFragment = new EditDialogFragment();
        mEditDialogFragment.setDialogType(EditDialogFragment.DIALOG_TYPE_DEFAULT);
        mEditDialogFragment.addTitle(getString(R.string.pls_input_admin_password));
        mEditDialogFragment.addEditText1(InputType.TYPE_CLASS_NUMBER, 8, null, true);
        mEditDialogFragment.setPositionText(getString(R.string.confirm));
        mEditDialogFragment.setNegativeText(getString(R.string.cancel));
        mEditDialogFragment.setDialogListener(new EditDialogFragment.OnDialogListener() {
            @Override
            public void onCancel() {
                mEditDialogFragment.dismiss();
            }

            @Override
            public void onConfirm(List<String> values) {
                Iterator<String> iterator = values.iterator();
                mPresenter.login2Setting(iterator.next());
            }
        });
        mEditDialogFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onLoginSuccess() {
        intent2Activity(UserListActivity.class);
    }

    @Override
    public void onLoginFailure() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_INTENT_2_USER, true);
        intent2Activity(LoginActivity.class, bundle);
    }

    @Override
    public void intent2Setting() {
        if (mEditDialogFragment != null && mEditDialogFragment.getDialog() != null) {
            Dialog dialog = mEditDialogFragment.getDialog();
            if (dialog.isShowing()) mEditDialogFragment.dismiss();
            mEditDialogFragment = null;
        }
        intent2Activity(SettingActivity.class);
    }

    @Override
    public void showMsg(String errMsg) {
        showToast(errMsg);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

}
