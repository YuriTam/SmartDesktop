package com.smart.desktop.client.activity.user;

import com.smart.desktop.base.BasePresenter;
import com.smart.desktop.common.constant.ParamKey;
import com.smart.desktop.core.api.IDataSource;
import com.smart.desktop.core.bean.UserInfo;

import java.util.List;

import static com.common.utils.Utils.checkNotNull;

/**
 * 接口实现
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年11月29日
 */
public class UserPresenter extends BasePresenter implements UserContract.Presenter {

    private final UserContract.View mView;

    public UserPresenter(UserContract.View view, IDataSource repository) {
        super(view, repository);
        mView = checkNotNull(view);
    }

    @Override
    public void onStart() {
        if (mIsFirstAction){
            postMainThread(() -> mView.onCurrentUser(mRepository.getParamValue(ParamKey.CURRENT_USER_NO, "")));
            getUserList();
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
    public void getUserList() {
        List<UserInfo> userList = mRepository.getUserList();
        if (userList == null) return;
        mLog.debug("操作员数量：" + userList.size());
        postMainThread(() -> mView.onUserList(userList));
    }

    @Override
    public void addUser() {

    }
}
