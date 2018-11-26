package com.smart.desktop.client.activity.user_add;

import com.smart.desktop.base.IBasePresenter;
import com.smart.desktop.base.IBaseView;

/**
 * 操作接口
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年10月9日
 */
public interface UserAddContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 添加成功
         */
        void addSuccess();

        /**
         * 提示信息
         *
         * @param errMsg 内容
         */
        void showMsg(String errMsg);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 添加操作员
         *
         * @param userNo 用户编号
         * @param password 密码
         */
        void addUser(String userNo, String password);

    }
}
