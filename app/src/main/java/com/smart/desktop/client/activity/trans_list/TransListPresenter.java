package com.smart.desktop.client.activity.trans_list;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.smart.desktop.R;
import com.smart.desktop.base.App;
import com.smart.desktop.base.BasePresenter;
import com.smart.desktop.common.constant.SysConstant;
import com.smart.desktop.core.api.IDataSource;
import com.smart.desktop.core.bean.TransRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.common.utils.Utils.checkNotNull;

/**
 * 交易列表相关操作
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年08月14日
 */
public class TransListPresenter extends BasePresenter implements TransListContract.Presenter {
    private Logger mLog = LoggerFactory.getLogger(TransListPresenter.class.getSimpleName());

    private TransListContract.View mView;

    private int currentPage = 0;  //当前页数
    private int currentCount = 0; //当前页交易记录数
    private boolean mIsFirstTime = true;

    public TransListPresenter(@NonNull TransListContract.View view, @NonNull IDataSource repository) {
        super(view, repository);
        mView = checkNotNull(view);
    }

    @Override
    public void onStart() {
        if (mIsFirstTime) {
            getTransList();
            mIsFirstTime = false;
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void refreshTransList() {
        currentCount = 0;
        currentPage = 0;
        postMainThread(() -> mView.showClearList());
        getTransList();
    }

    @Override
    public void getTransList() {
        List<TransRecord> transList = null;
        if (transList == null || transList.size() == 0) {
            postMainThread(() -> mView.showMsg("暂无交易数据"));
            postMainThread(() -> mView.showEmptyList());
            return;
        }
        currentPage = 1;
        currentCount = transList.size();
        mLog.debug("当前页数：{}，当前页交易记录数：{}", currentPage, currentCount);
        postMainThread(() -> mView.onTransList(transList, false));
    }

    @Override
    public void getMoreTransList() {
        if (currentCount < SysConstant.MAX_PAGE_SIZE) {
            postMainThread(() -> mView.showNotMoreData());
            return;
        }
        List<TransRecord> transList = null;
        if (transList == null || transList.size() == 0) {
            postMainThread(() -> mView.showNotMoreData());
            return;
        }
        ++currentPage;
        currentCount = transList.size();
        mLog.debug("当前页数：{}，当前页交易记录数：{}", currentPage, currentCount);
        postMainThread(() -> mView.onTransList(transList, false));
    }

    @Override
    public void getTransRecord(String traceNum) {
        if (TextUtils.isEmpty(traceNum)) {
            postMainThread(() -> mView.showMsg(App.sContext.getString(R.string.input_is_empty)));
            return;
        }
        if (!TextUtils.isDigitsOnly(traceNum)) {
            postMainThread(() -> mView.showMsg("输入格式不正确"));
            return;
        }
        TransRecord transRecord = null;
        if (transRecord == null || !transRecord.getIsSuccess()) {
            postMainThread(() -> mView.showMsg("该流水号不存在"));
            return;
        }
        List<TransRecord> transList = new ArrayList<>();
        transList.add(transRecord);
        currentPage = 1;
        currentCount = transList.size();
        mLog.debug("当前页数：{}，当前页交易记录数：{}", currentPage, currentCount);
        postMainThread(() -> mView.onTransList(transList, true));
    }

}
