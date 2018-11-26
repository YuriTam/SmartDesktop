package com.smart.desktop.client.activity.user_list;

import com.smart.desktop.R;
import com.smart.desktop.base.App;
import com.smart.desktop.base.BasePresenter;
import com.smart.desktop.common.constant.ParamKey;
import com.smart.desktop.common.enums.UserType;
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
public class UserListPresenter extends BasePresenter implements UserListContract.Presenter {

    private final UserListContract.View mView;

    public UserListPresenter(UserListContract.View view, IDataSource repository) {
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
    public void editUser(String userNo) {
        String currentUserNo = mRepository.getParamValue(ParamKey.CURRENT_USER_NO, "");
        //判断是否为主管操作员（只要主管及自己可以编辑自己的信息）
        if (!UserType.SUPERVISOR.getUserNo().equals(currentUserNo)
                && !userNo.equals(currentUserNo)){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.can_no_edit_unless_supervisor)));
            return;
        }
        postMainThread(() -> mView.onEditUser(userNo));
    }

    @Override
    public void deleteUser(String userNo) {
        //判断是否为主管操作员（只要主管可以删除）
        if (!UserType.SUPERVISOR.getUserNo().equals(mRepository.getParamValue(ParamKey.CURRENT_USER_NO, ""))){
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.can_no_delete_unless_supervisor)));
            return;
        }
        mRepository.deleteByUserId(userNo);
        postMainThread(() -> mView.onDeleteSuccess());
    }
}
