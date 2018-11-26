package com.smart.desktop.client.activity.user_list;

import com.smart.desktop.base.IBasePresenter;
import com.smart.desktop.base.IBaseView;
import com.smart.desktop.core.bean.UserInfo;

import java.util.List;

/**
 * 操作接口
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年10月9日
 */
public interface UserListContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 返回当前操作员
         *
         * @param userNo
         */
        void onCurrentUser(String userNo);

        /**
         * 返回操作员列表
         *
         * @param userList
         */
        void onUserList(List<UserInfo> userList);

        /**
         * 删除成功
         */
        void onDeleteSuccess();

        /**
         * 编辑
         *
         * @param userNo 编号
         */
        void onEditUser(String userNo);

        /**
         * 提示信息
         *
         * @param errMsg 内容
         */
        void showMsg(String errMsg);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 获取操作员列表
         */
        void getUserList();

        /**
         * 编辑操作员
         *
         * @param userNo
         */
        void editUser(String userNo);

        /**
         * 删除操作员
         *
         * @param userNo
         */
        void deleteUser(String userNo);

    }
}
