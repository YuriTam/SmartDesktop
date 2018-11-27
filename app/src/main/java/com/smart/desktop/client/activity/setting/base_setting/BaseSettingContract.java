package com.smart.desktop.client.activity.setting.base_setting;

import com.smart.desktop.base.IBasePresenter;
import com.smart.desktop.base.IBaseView;

import java.util.Map;

/**
 * 操作接口
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年10月9日
 */
public interface BaseSettingContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 初始化数据
         */
        void showDetail();

        /**
         * 获取需要保存的参数
         */
        Map<String, String> getBindData();

        /**
         * 保存成功
         */
        void saveSuccess();

        /**
         * 提示信息
         *
         * @param errMsg 内容
         */
        void showMsg(String errMsg);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 获取参数值
         *
         * @param key
         * @param defValue
         * @return
         */
        String getParam(String key, String defValue);

        /**
         * 保存参数
         *
         * @param params 参数集
         */
        void saveParam(Map<String, String> params);

    }
}
