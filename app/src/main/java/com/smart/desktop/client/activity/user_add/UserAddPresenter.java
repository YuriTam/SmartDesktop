package com.smart.desktop.client.activity.user_add;

import android.text.TextUtils;

import com.smart.desktop.R;
import com.smart.desktop.base.App;
import com.smart.desktop.base.BasePresenter;
import com.smart.desktop.core.api.IDataSource;
import com.smart.desktop.core.bean.UserInfo;

import static com.common.utils.Utils.checkNotNull;

/**
 * 接口实现
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年11月29日
 */
public class UserAddPresenter extends BasePresenter implements UserAddContract.Presenter {

    private final UserAddContract.View mView;

    public UserAddPresenter(UserAddContract.View view, IDataSource repository) {
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
    public void addUser(String userNo, String password) {
        if (TextUtils.isEmpty(userNo)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.input_user_no_is_empty)));
            return;
        }
        if (TextUtils.isEmpty(password)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.input_password_is_empty)));
            return;
        }
        //判断操作员编号是否已存在
        UserInfo info = mRepository.getUserInfo(userNo);
        if (info != null){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.user_no_is_exist)));
            return;
        }
        info = new UserInfo(userNo, password);
        mRepository.saveUser(info);
        postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.add_success)));
        postMainThread(() -> mView.addSuccess());
    }
}
