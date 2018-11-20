package com.smart.desktop.client.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.common.widget.TitleBuilder;
import com.stx.xhb.xbanner.XBanner;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月20日
 */
public class MainActivity extends BaseActivity implements XBanner.XBannerAdapter {

    private Integer[] mImages = {R.mipmap.ad_point, R.mipmap.ad_quick, R.mipmap.ad_union};

    @BindView(R.id.banner)
    XBanner mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        new TitleBuilder(this)
                .setRightImage(R.drawable.user_icon)
                .setTitleText("智能收银台")
                .build();
    }

    @Override
    protected void initEvent() {

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

    @OnClick({R.id.title_iv_right, R.id.btn_merchant, R.id.btn_trans_detail,
            R.id.btn_sys_setting, R.id.btn_version})
    public void onViewClicked(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.title_iv_right:
                showToast("用户信息");
                break;
            case R.id.btn_merchant:
                showToast("商户信息");
                break;
            case R.id.btn_trans_detail:
                showToast("交易明细");
                break;
            case R.id.btn_sys_setting:
                showToast("系统设置");
                break;
            case R.id.btn_version:
                showToast("版本信息");
                break;
            default:
                break;
        }
    }
}
