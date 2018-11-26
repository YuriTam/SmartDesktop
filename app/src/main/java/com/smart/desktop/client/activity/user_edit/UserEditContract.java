package com.smart.desktop.client.activity.user_edit;

import com.smart.desktop.base.IBasePresenter;
import com.smart.desktop.base.IBaseView;

/**
 * 操作接口
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年10月9日
 */
public interface UserEditContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 编辑成功
         */
        void onEditSuccess();

        /**
         * 提示信息
         *
         * @param errMsg 内容
         */
        void showMsg(String errMsg);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 编辑密码
         *
         * @param passwordOne 密码
         * @param passwordTwo 密码
         */
        void editUser(String passwordOne, String passwordTwo);

    }
}
