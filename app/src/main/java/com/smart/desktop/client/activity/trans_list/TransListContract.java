package com.smart.desktop.client.activity.trans_list;

import com.smart.desktop.base.IBasePresenter;
import com.smart.desktop.base.IBaseView;
import com.smart.desktop.core.bean.TransRecord;

import java.util.List;

/**
 * 交易列表接口
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年08月14日
 */
public class TransListContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 返回交易记录列表
         *
         * @param transList 交易列表
         * @param isSearch  是否查询操作（查询成功返回一条记录）
         */
        void onTransList(List<TransRecord> transList, boolean isSearch);

        /**
         * 清空列表数据
         */
        void showClearList();

        /**
         * 提示没有更多数据
         */
        void showNotMoreData();

        /**
         * 提示暂无交易记录
         */
        void showEmptyList();

        /**
         * 提示
         *
         * @param errMsg 内容
         */
        void showMsg(String errMsg);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 重新获取交易列表
         */
        void refreshTransList();

        /**
         * 查询首页交易记录(默认每页为20条)
         */
        void getTransList();

        /**
         * 查询更多数据（即下页的数据）
         */
        void getMoreTransList();

        /**
         * 根据凭证号查询该交易记录
         */
        void getTransRecord(String traceNum);

    }
}
