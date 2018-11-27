package com.smart.desktop.client.activity.setting.settle;

import android.os.Bundle;
import android.view.View;

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
 * 结算相关设置
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月27日
 */
public class SettleSettingActivity extends BaseSettingActivity {

    @BindView(R.id.check_settle_day)
    SwitchButton checkSettleDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_settle_setting;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setLeftImage(R.drawable.arrow_icon)
                .setTitleText(getString(R.string.settle_param_setting))
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
        checkSettleDay.setChecked("1".equals(mPresenter.getParam(ParamKey.IS_SETTLE_CURRENT_DATE, "1")));
    }

    @Override
    public Map<String, String> getBindData() {
        Map<String, String> params = new HashMap<>();
        params.put(ParamKey.IS_SETTLE_CURRENT_DATE, checkSettleDay.isChecked() ? "1" : "0");
        return params;
    }

}
