package com.smart.desktop.client.activity.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.client.activity.setting.print.PrintSettingActivity;
import com.smart.desktop.client.activity.setting.settle.SettleSettingActivity;
import com.smart.desktop.client.activity.setting.signature.SignatureSettingActivity;
import com.smart.desktop.common.widget.TitleBuilder;

import butterknife.OnClick;

/**
 * 系统设置
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年12月21日
 */
public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setLeftImage(R.drawable.arrow_icon)
                .setTitleText(getString(R.string.system_setting))
                .build();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.all_wifi, R.id.all_network, R.id.all_display, R.id.all_auth, R.id.all_device_info,
            R.id.title_iv_left, R.id.all_signature, R.id.all_print, R.id.all_settle})
    public void onViewClicked(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.title_iv_left:
                finish();
                break;
            case R.id.all_wifi:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            case R.id.all_network:
                startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                break;
            case R.id.all_display:
                startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
                break;
            case R.id.all_auth:
                startActivity(getAppDetailSettingIntent());
                break;
            case R.id.all_device_info:
                startActivity(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS));
                break;
            case R.id.all_signature:
                intent2Activity(SignatureSettingActivity.class);
                break;
            case R.id.all_print:
                intent2Activity(PrintSettingActivity.class);
                break;
            case R.id.all_settle:
                intent2Activity(SettleSettingActivity.class);
                break;
            default:
                break;
        }
    }

    private Intent getAppDetailSettingIntent(){
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }
}
