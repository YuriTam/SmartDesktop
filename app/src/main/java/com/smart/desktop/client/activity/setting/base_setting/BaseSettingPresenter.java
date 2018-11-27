package com.smart.desktop.client.activity.setting.base_setting;

import com.smart.desktop.base.BasePresenter;
import com.smart.desktop.core.api.IDataSource;

import java.util.Map;

import static com.common.utils.Utils.checkNotNull;

/**
 * 接口实现
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年11月29日
 */
public class BaseSettingPresenter extends BasePresenter implements BaseSettingContract.Presenter {

    private final BaseSettingContract.View mView;

    public BaseSettingPresenter(BaseSettingContract.View view, IDataSource repository) {
        super(view, repository);
        mView = checkNotNull(view);
    }

    @Override
    public void onStart() {
        if (mIsFirstAction){
            postMainThread(() -> mView.showDetail());
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
    public String getParam(String key, String defValue) {
        return getParamValue(key, defValue);
    }

    @Override
    public void saveParam(Map<String, String> params) {
        if (params == null) return;
        String value;
        for (String key : params.keySet()){
            value = params.get(key);
            mLog.debug("key = {}, value = {}", key, value);
            saveParamValue(key, value);
        }
        postMainThread(() -> mView.saveSuccess());
    }

}
