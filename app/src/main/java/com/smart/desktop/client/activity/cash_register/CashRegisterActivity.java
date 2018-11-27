package com.smart.desktop.client.activity.cash_register;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.common.constant.SysConstant;
import com.smart.desktop.common.widget.TitleBuilder;
import com.smart.desktop.common.widget.keyboard.NumKeyBoard;
import com.smart.desktop.common.widget.keyboard.NumKeyBoardWithTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 收银界面
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月27日
 */
public class CashRegisterActivity extends BaseActivity {

    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.key_board)
    NumKeyBoardWithTextView keyBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_cash_register;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setLeftImage(R.drawable.arrow_icon)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setTitleText(getString(R.string.checkout_counter))
                .build();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        keyBoard.setTextView(tvAmount);
        // 设置小数点位数
        keyBoard.setFloat(SysConstant.FUND_DIGITS);
        // 设置可输入的最大长度
        keyBoard.setMaxLen(12);
        keyBoard.setMaxValue(999999999999L);
        keyBoard.setCallback(mKeyBoardCallback);
    }

    /**
     * 键盘回调
     */
    private NumKeyBoardWithTextView.Callback mKeyBoardCallback = new NumKeyBoard.Callback() {
        @Override
        public void onKey(NumKeyBoard.KEY_TYPE keyType) {
            switch (keyType) {
                case KEY_CANCEL:
                    finish();
                    break;
                case KEY_CONFIRM:

                    break;
                default:
                    break;
            }
        }
    };

    @OnClick({R.id.title_iv_left, R.id.ll_qrcode, R.id.ll_scan, R.id.ll_bankcard})
    public void onViewClicked(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.title_iv_left:
                finish();
                break;
            case R.id.ll_qrcode:
                break;
            case R.id.ll_scan:
                break;
            case R.id.ll_bankcard:
                break;
            default:
                break;
        }
    }

    /**
     * 确认交易金额
     */
    private void confirmAmount(){
        // 如果输入的金额是0，则不要做任何处理
        long retLong = Long.parseLong(keyBoard.getResult());
        if (retLong == 0) return;
        showToast(keyBoard.getResult());
    }

}
