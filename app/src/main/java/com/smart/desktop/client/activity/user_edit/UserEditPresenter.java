package com.smart.desktop.client.activity.user_edit;

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
public class UserEditPresenter extends BasePresenter implements UserEditContract.Presenter {

    private final UserEditContract.View mView;
    private final String mUserNo;

    public UserEditPresenter(UserEditContract.View view, IDataSource repository, String userNo) {
        super(view, repository);
        mView = checkNotNull(view);
        mUserNo = userNo;
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
    public void editUser(String passwordOne, String passwordTwo) {
        if (TextUtils.isEmpty(passwordOne) || TextUtils.isEmpty(passwordTwo)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.input_password_is_empty)));
            return;
        }
        //判断密码是否一致
        if (!passwordOne.equals(passwordTwo)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.input_password_is_no_eq)));
            return;
        }
        UserInfo info = mRepository.getUserInfo(mUserNo);
        info.setPassword(passwordOne);
        mRepository.updateUserInfo(info);
        postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.edit_success)));
        postMainThread(() -> mView.onEditSuccess());
    }
}
