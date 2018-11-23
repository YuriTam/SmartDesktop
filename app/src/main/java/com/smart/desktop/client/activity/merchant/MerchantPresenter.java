package com.smart.desktop.client.activity.merchant;

import com.smart.desktop.base.BasePresenter;
import com.smart.desktop.common.constant.ParamKey;
import com.smart.desktop.common.utils.TransUtils;
import com.smart.desktop.core.api.IDataSource;
import com.smart.desktop.core.bean.MerchantInfo;

import static com.common.utils.Utils.checkNotNull;

/**
 * 接口实现
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年11月29日
 */
public class MerchantPresenter extends BasePresenter implements MerchantContract.Presenter {

    private final MerchantContract.View mView;

    public MerchantPresenter(MerchantContract.View view, IDataSource repository) {
        super(view, repository);
        mView = checkNotNull(view);
    }

    @Override
    public void onStart() {
        if (mIsFirstAction){
            getMerchantInfo();
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
    public void getMerchantInfo() {
        // FIXME: 2018/11/23 测试数据
        mRepository.saveParamValue(ParamKey.MERCHANT_NAME, "深圳天躏集团测试商户");
        mRepository.saveParamValue(ParamKey.MERCHANT_ID, "653256365669845");
        mRepository.saveParamValue(ParamKey.TERMINAL_ID, "56856122");
        mRepository.saveParamValue(ParamKey.MERCHANT_USER_NAME, "刘大跃");
        mRepository.saveParamValue(ParamKey.BANK_ACCOUNT_NO, "6221532356562152");
        mRepository.saveParamValue(ParamKey.MERCHANT_ADDRESS, "深圳市南山区高新科技园中钢大厦3楼盛迪嘉");
        mRepository.syncParamValue();

        MerchantInfo info = new MerchantInfo();
        info.setMerchantName(mRepository.getParamValue(ParamKey.MERCHANT_NAME, ""));
        info.setMerchantNo(mRepository.getParamValue(ParamKey.MERCHANT_ID, ""));
        info.setTerminalNo(mRepository.getParamValue(ParamKey.TERMINAL_ID, ""));
        info.setMerchantUserName(mRepository.getParamValue(ParamKey.MERCHANT_USER_NAME, ""));
        info.setBankAccountNo(TransUtils.maskPan(mRepository.getParamValue(ParamKey.BANK_ACCOUNT_NO, "")));
        info.setMerchantAddress(mRepository.getParamValue(ParamKey.MERCHANT_ADDRESS, ""));
        postMainThread(() -> mView.showMerchantInfo(info));
    }
}
