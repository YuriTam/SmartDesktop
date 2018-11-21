package com.smart.desktop.client.activity.login;

import com.smart.desktop.base.IBasePresenter;
import com.smart.desktop.base.IBaseView;

/**
 * 操作接口
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年10月9日
 */
public interface LoginContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 登录成功
         */
        void onSuccess();

        /**
         * 提示消息
         *
         * @param errMsg 提示内容
         */
        void showMsg(String errMsg);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 登录
         */
        void login(String userNo, String password);

    }
}
