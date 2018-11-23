package com.smart.desktop.client.activity.merchant;

import com.smart.desktop.base.IBasePresenter;
import com.smart.desktop.base.IBaseView;
import com.smart.desktop.core.bean.MerchantInfo;

/**
 * 操作接口
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年10月9日
 */
public interface MerchantContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 商户信息回调
         *
         * @param info
         */
        void showMerchantInfo(MerchantInfo info);

        /**
         * 提示信息
         *
         * @param errMsg 内容
         */
        void showMsg(String errMsg);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 获取商户信息
         */
        void getMerchantInfo();

    }
}
