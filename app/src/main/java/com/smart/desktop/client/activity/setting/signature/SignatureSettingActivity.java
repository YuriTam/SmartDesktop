package com.smart.desktop.client.activity.setting.signature;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.client.activity.setting.base_setting.BaseSettingActivity;
import com.smart.desktop.common.constant.ParamKey;
import com.smart.desktop.common.widget.SwitchButton;
import com.smart.desktop.common.widget.TitleBuilder;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 电子签名参数设置
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月27日
 */
public class SignatureSettingActivity extends BaseSettingActivity {

    @BindView(R.id.check_support_signature)
    SwitchButton checkSupportSignature;
    @BindView(R.id.check_show_signature)
    SwitchButton checkShowSignature;
    @BindView(R.id.et_time_out)
    EditText etTimeOut;
    @BindView(R.id.et_max_times)
    EditText etMaxTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_signature_setting;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setLeftImage(R.drawable.arrow_icon)
                .setTitleText(getString(R.string.setup_signature_title))
                .setRightText(getString(R.string.save))
                .build();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.title_iv_left, R.id.title_tv_right})
    public void onViewClicked(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.title_iv_left:
                finish();
                break;
            case R.id.title_tv_right:
                mPresenter.saveParam(getBindData());
                break;
            default:
                break;
        }
    }

    @Override
    public void showDetail() {
        checkSupportSignature.setChecked("1".equals(mPresenter.getParam(ParamKey.IS_SUPPORT_ELEC_SIGNATURE, "1")));
        checkShowSignature.setChecked("1".equals(mPresenter.getParam(ParamKey.IS_SHOW_CONFIRM_SIGNATURE, "0")));
        etTimeOut.setText(mPresenter.getParam(ParamKey.SIGNATURE_TIME_OUT, "60"));
        etMaxTimes.setText(mPresenter.getParam(ParamKey.MAX_SIGNATURE_TIMES, "3"));
    }

    @Override
    public Map<String, String> getBindData() {
        Map<String, String> params = new HashMap<>();
        params.put(ParamKey.IS_SUPPORT_ELEC_SIGNATURE, checkSupportSignature.isChecked() ? "1" : "0");
        params.put(ParamKey.IS_SHOW_CONFIRM_SIGNATURE, checkShowSignature.isChecked() ? "1" : "0");
        params.put(ParamKey.SIGNATURE_TIME_OUT, etTimeOut.getText().toString());
        params.put(ParamKey.MAX_SIGNATURE_TIMES, etMaxTimes.getText().toString());
        return params;
    }
}
