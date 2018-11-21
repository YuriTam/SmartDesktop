package com.smart.desktop.client.activity.main;

import com.smart.desktop.base.IBasePresenter;
import com.smart.desktop.base.IBaseView;

/**
 * 操作接口
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年10月9日
 */
public interface MainContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 已登录
         */
        void onLoginSuccess();

        /**
         * 未登录
         */
        void onLoginFailure();

        /**
         * 跳转到系统设置
         */
        void intent2Setting();

        /**
         * 提示信息
         *
         * @param errMsg 内容
         */
        void showMsg(String errMsg);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 验证管理员密码
         *
         * @param password 密码
         */
        void login2Setting(String password);

        /**
         * 跳转到用户信息界面
         */
        void intent2UserInfo();

    }
}
