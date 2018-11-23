package com.smart.desktop.client.activity.main;

import android.text.TextUtils;

import com.smart.desktop.R;
import com.smart.desktop.base.App;
import com.smart.desktop.base.BasePresenter;
import com.smart.desktop.common.constant.ParamKey;
import com.smart.desktop.core.api.IDataSource;

import static com.common.utils.Utils.checkNotNull;

/**
 * 接口实现
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年11月29日
 */
public class MainPresenter extends BasePresenter implements MainContract.Presenter {

    private final MainContract.View mView;

    public MainPresenter(MainContract.View view, IDataSource repository) {
        super(view, repository);
        mView = checkNotNull(view);
    }

    @Override
    public void onStart() {
        if (mIsFirstAction){

            mIsFirstAction = false;
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void login2Setting(String password) {
        if (TextUtils.isEmpty(password)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.input_password_is_empty)));
            return;
        }
        if (!"12345678".equals(password)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.password_error)));
            return;
        }
        postMainThread(() -> mView.intent2Setting());
    }

    @Override
    public void intent2UserInfo() {
        //判断是否已经登录
        if (!TextUtils.isEmpty(mRepository.getParamValue(ParamKey.CURRENT_USER_NO, ""))){
            mLog.debug("已签到");
            postMainThread(() -> mView.onLoginSuccess());
            return;
        }
        mLog.debug("未签到");
        postMainThread(() -> mView.onLoginFailure());
    }
}
