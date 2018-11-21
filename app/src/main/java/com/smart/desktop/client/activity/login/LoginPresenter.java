package com.smart.desktop.client.activity.login;

import android.text.TextUtils;

import com.smart.desktop.R;
import com.smart.desktop.base.App;
import com.smart.desktop.base.BasePresenter;
import com.smart.desktop.common.constant.ParamKey;
import com.smart.desktop.common.utils.StringUtils;
import com.smart.desktop.core.api.IDataSource;

import static com.common.utils.Utils.checkNotNull;

/**
 * 接口实现
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年11月29日
 */
public class LoginPresenter extends BasePresenter implements LoginContract.Presenter {

    private final LoginContract.View mView;

    public LoginPresenter(LoginContract.View view, IDataSource repository) {
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
    public void login(String userNo, String password) {
        if (TextUtils.isEmpty(userNo)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.input_user_no_is_empty)));
            return;
        }
        if (TextUtils.isEmpty(password)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.input_password_is_empty)));
            return;
        }
        userNo = StringUtils.leftPad(userNo, 2, '0');
        mLog.debug("登录：userNo = {}, password = {}", userNo, password);
        if (!"0000".equals(password)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.password_error)));
            return;
        }
        //更新登录状态
        saveParamValue(ParamKey.LOGIN_STATUS, "1");
        postMainThread(() -> mView.onSuccess());
    }

}
