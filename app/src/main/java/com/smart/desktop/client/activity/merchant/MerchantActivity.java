package com.smart.desktop.client.activity.merchant;

import android.os.Bundle;
import android.view.View;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.common.widget.ItemLinearLayout;
import com.smart.desktop.common.widget.TitleBuilder;
import com.smart.desktop.core.api.ApiRepository;
import com.smart.desktop.core.bean.MerchantInfo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商户信息
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年12月21日
 */
public class MerchantActivity extends BaseActivity implements MerchantContract.View {

    @BindView(R.id.merchant_name)
    ItemLinearLayout merchantName;
    @BindView(R.id.merchant_no)
    ItemLinearLayout merchantNo;
    @BindView(R.id.terminal_no)
    ItemLinearLayout terminalNo;
    @BindView(R.id.merchant_user_name)
    ItemLinearLayout merchantUserName;
    @BindView(R.id.bank_account_no)
    ItemLinearLayout bankAccountNo;
    @BindView(R.id.merchant_address)
    ItemLinearLayout merchantAddress;

    private MerchantContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MerchantPresenter(this, ApiRepository.getInstance());
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

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onStart();
    }

    @OnClick({R.id.title_iv_left})
    public void onClick(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.title_iv_left:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showMerchantInfo(MerchantInfo info) {
        if (info == null) return;
        merchantName.setRightText(info.getMerchantName());
        merchantNo.setRightText(info.getMerchantNo());
        terminalNo.setRightText(info.getTerminalNo());
        merchantUserName.setRightText(info.getMerchantUserName());
        bankAccountNo.setRightText(info.getBankAccountNo());
        merchantAddress.setRightText(info.getMerchantAddress());
    }

    @Override
    public void showMsg(String errMsg) {
        showToast(errMsg);
    }

    @Override
    public void setPresenter(MerchantContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
