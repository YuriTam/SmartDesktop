package com.smart.desktop.client.activity.version;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.utils.AppUtils;
import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.App;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.common.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 版本信息
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月21日
 */
public class VersionActivity extends BaseActivity {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_name_version)
    TextView tvNameVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_version;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setLeftImage(R.drawable.arrow_icon)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setTitleText(getString(R.string.version_info))
                .build();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        tvNameVersion.setText(getString(R.string.app_name) + "\nv" + AppUtils.getAppVersionName(App.sContext));
    }

    @OnClick({R.id.title_iv_left, R.id.all_check_update})
    public void onClick(View view){
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()){
            case R.id.title_iv_left:
                finish();
                break;
            case R.id.all_check_update:
                showToast(getString(R.string.is_newest_version));
                break;
            default:
                break;
        }
    }

}
