package com.smart.desktop.client.activity.setting.print;

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
 * 打印相关设置
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月26日
 */
public class PrintSettingActivity extends BaseSettingActivity {

    @BindView(R.id.check_print_sales_slip)
    SwitchButton checkPrintSalesSlip;
    @BindView(R.id.et_service_hot_line)
    EditText etServiceHotLine;
    @BindView(R.id.print_times)
    EditText printTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_print_setting;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setLeftImage(R.drawable.arrow_icon)
                .setTitleText(getString(R.string.print_param_setting))
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
        checkPrintSalesSlip.setChecked("1".equals(mPresenter.getParam(ParamKey.IS_SUPPORT_PRINT, "1")));
        etServiceHotLine.setText(mPresenter.getParam(ParamKey.PRINT_HOT_LINE, ""));
        printTimes.setText(mPresenter.getParam(ParamKey.PRINT_TIMES, "2"));
    }

    @Override
    public Map<String, String> getBindData() {
        Map<String, String> params = new HashMap<>();
        params.put(ParamKey.IS_SUPPORT_PRINT, checkPrintSalesSlip.isChecked() ? "1" : "0");
        params.put(ParamKey.PRINT_HOT_LINE, etServiceHotLine.getText().toString());
        params.put(ParamKey.PRINT_TIMES, printTimes.getText().toString());
        return params;
    }

}
