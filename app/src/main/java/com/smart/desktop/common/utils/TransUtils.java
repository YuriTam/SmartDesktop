package com.smart.desktop.common.utils;

import android.text.TextUtils;

import com.smart.desktop.common.enums.TransType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java8.util.Optional;

/**
 * 交易工具类
 */
public class TransUtils {
    private final static Logger mLog = LoggerFactory.getLogger(TransUtils.class.getSimpleName());

    /**
     * 前6后4， 其他显示“*”
     *
     * @param pan
     * @return
     */
    public static String maskPan(String pan) {
        return Optional.ofNullable(pan)
                .filter(s -> s.length() > 10)
                .map(s -> {
                    int maskLen = s.length() - 10;
                    StringBuilder padded = new StringBuilder();
                    while (padded.length() < maskLen) {
                        padded.insert(0, '*');
                    }
                    return new StringBuilder(s).replace(6, s.length() - 4, padded.toString()).toString();
                })
                .orElse(pan);
    }

    /**
     * 预授权交易卡号格式化
     *
     * @param pan
     * @return
     */
    public static String formatPan(String pan){
        return Optional.ofNullable(pan)
                .filter(s -> !TextUtils.isEmpty(s))
                .map(s -> {
                    StringBuilder sb = new StringBuilder(s);
                    int index;
                    for (index = 4; index < sb.length(); index += 5){
                        sb.insert(index, " ");
                    }
                    return sb.toString();
                })
                .orElse(pan);
    }

    /**
     * 根据刷卡方式获取对应值
     *
     * @param entryMode
     * @return
     */
    public static String getCardEntryModeSymbol(String entryMode) {
        return Optional.ofNullable(entryMode)
                .map(s -> s.substring(0, 2))
                .map(s -> {
                    switch (s) {
                        case "01": //手输
                            return "（M）";
                        case "02": //刷卡
                            return "（S）";
                        case "05": //插卡
                            return "（I）";
                        case "07": //非接
                        case "98":
                        case "96":
                            return "（C）";
                        case "92":
                            return " (N)";
                        default:
                            return "";
                    }
                })
                .orElse("");
    }

    /**
     * 获取打印单上边交易的符号
     *
     * @param transType 交易类型
     * @return
     */
    public static String getTransTypeSymbol(TransType transType) {
        String type;
        switch (transType) {
            case AUTH_CM:
                type = "P";
                break;
            case AUTH_CM_NOTIFY:
                type = "C";
                break;
            case EC_SALE:
                type = "E";
                break;
            case SALE:
            case SCAN_SALE:
                type = "S";
                break;
            case REFUND:
            case SCAN_REFUND:
                type = "R";
                break;
            default:
                type = "";
                break;
        }
        return type;
    }

    /**
     * 是否撤销类交易
     *
     * @param transType 交易类型
     * @return
     */
    public static boolean isVoidTrans(TransType transType) {
        switch (transType) {
            case VOID:
            case AUTH_CM_VOID:
            case AUTH_VOID:
            case SCAN_VOID:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否退货类交易
     *
     * @param transType 交易类型
     * @return
     */
    public static boolean isRefundTrans(TransType transType) {
        switch (transType) {
            case REFUND:
            case SCAN_REFUND:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否预授权类交易
     *
     * @param transType 交易类型
     * @return
     */
    public static boolean isAuthTrans(TransType transType) {
        switch (transType) {
            case AUTH:
            case AUTH_VOID:
            case AUTH_CM:
            case AUTH_CM_NOTIFY:
            case AUTH_CM_VOID:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否通知类交易
     *
     * @param transType 交易类型
     * @return
     */
    public static boolean isNotifyTrans(TransType transType) {
        switch (transType) {
            case AUTH_CM_NOTIFY:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否 qps 支持的交易类型
     *
     * @param transType 交易类型
     * @return
     */
    public static boolean isQPSSupportTrans(TransType transType) {
        switch (transType) {
            case SALE:
            case AUTH:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否扫码类交易
     *
     * @param transType 交易类型
     * @return
     */
    public static boolean isScanTrans(TransType transType) {
        switch (transType) {
            case SCAN_SALE:
            case SCAN_REFUND:
            case SCAN_VOID:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否显示打印交易明显的交易
     *
     * @param transType 交易类型
     * @return
     */
    public static boolean isNeedPrintDetail(TransType transType) {
        switch (transType) {
            case SALE:
            case SCAN_SALE:
            case OFF_SALE:
            case EC_SALE:
            case AUTH_CM:
            case REFUND:
            case SCAN_REFUND:
                return true;
            default:
                return false;
        }
    }

    /**
     * 通过响应码判断是否需要交易签到
     *
     * @param responseCode 响应码
     * @return
     */
    public static boolean isNeedReLogin(String responseCode) {
        return "A0".equals(responseCode);
    }

    /**
     * 通过响应码判断是否后台返回密码错
     *
     * @param responseCode 响应码
     * @return
     */
    public static boolean isPasswordIncorrect(String responseCode) {
        return "55".equals(responseCode);
    }

    /**
     * 是否为部分成功交易
     *
     * @param responseCode
     * @return
     */
    public static boolean isPartSuccessTrans(String responseCode){
        return "10".equals(responseCode);
    }

    /**
     * 通过响应码判断是否重复交易
     *
     * @param responseCode 响应码
     * @return
     */
    public static boolean isRepeatedTrans(String responseCode) {
        return "94".equals(responseCode);
    }

    /**
     * 通过操作员号判断是否主管
     *
     * @param operator 操作员号
     * @return
     */
    public static boolean isAdminOperator(String operator) {
        return "00".equals(operator);
    }

    /**
     * 通过操作员号判断是否系统管理员
     *
     * @param operator 操作员号
     * @return
     */
    public static boolean isSystemOperator(String operator) {
        return "99".equals(operator);
    }

    /**
     * 获取用卡方式，22域
     *
     * @return
     */
//    public static String getEntryMode(CardMode cardMode, EmvTransFlowEnum flow, boolean hasPin) {
//        String field = "";
//        switch (cardMode) {
//            case MANUAL:
//                field += "01";
//                break;
//            case SWIPE:
//                field += "02";
//                break;
//            case INSERT:
//                field += "05";
//                break;
//            case TAP:
//                if (EmvTransFlowEnum.FULL.equals(flow)) {
//                    field += "98";
//                } else {
//                    field += "07";
//                }
//                break;
//            case SCAN:
//                field += "03";
//                break;
//            default:
//                field += "01";
//                break;
//        }
//        if (hasPin) {
//            field += "1";
//        } else {
//            field += "2";
//        }
//        return field;
//    }

    /**
     * 通过用卡方式判断是不是IC卡交易
     *
     * @param entryMode 用卡方式
     * @return
     */
    public static boolean isICCTransByEntryMode(String entryMode) {
        return entryMode.startsWith("05")
                || entryMode.startsWith("98")
                || entryMode.startsWith("07");
    }

    /**
     * 判断是否当前交易是否已开启支持
     *
     * @param transType  交易类型
     * @param repository 仓库
     * @return
     */
//    public static boolean isSupportTrans(TransType transType, DataSource repository) {
//        switch (transType) {
//            case SALE:
//            case OFF_SALE:
//            case EC_SALE:
//            case SCAN_SALE:
//                return "1".equals(repository.getParamValue(PreferencesConstants.SALE_SWITCH));
//            case VOID:
//            case SCAN_VOID:
//                return "1".equals(repository.getParamValue(PreferencesConstants.SALE_VOID_SWITCH));
//            case REFUND:
//            case SCAN_REFUND:
//                return "1".equals(repository.getParamValue(PreferencesConstants.REFUND_SWITCH));
//            case QUERY:
//                return "1".equals(repository.getParamValue(PreferencesConstants.QUERY_SWITCH));
//            case AUTH:
//                return "1".equals(repository.getParamValue(PreferencesConstants.PRE_AUTH_SWITCH));
//            case AUTH_VOID:
//                return "1".equals(repository.getParamValue(PreferencesConstants.PRE_AUTH_VOID_SWITCH));
//            case AUTH_CM:
//                return "1".equals(repository.getParamValue(PreferencesConstants.PRE_AUTH_COMPLETE_SWITCH));
//            case AUTH_CM_VOID:
//                return "1".equals(repository.getParamValue(PreferencesConstants.PRE_AUTH_VOID_SWITCH));
//            case AUTH_CM_NOTIFY:
//                return "1".equals(repository.getParamValue(PreferencesConstants.PRE_AUTH_COMPLETE_NOTIFY_SWITCH));
//            default:
//                return true;
//        }
//    }

    /**
     * 判断交易是否根据设置不用输入密码
     *
     * @param transType  交易类型
     * @param repository 仓库
     * @return
     */
//    public static boolean isTransNeedPin(TransType transType, DataSource repository) {
//        switch (transType) {
//            case VOID:
//            case SCAN_VOID:
//                return "1".equals(repository.getParamValue(PreferencesConstants.VOID_NEED_PASSWORD));
//            case AUTH_VOID:
//                return "1".equals(repository.getParamValue(PreferencesConstants.PRE_AUTH_VOID_NEED_PASSWORD));
//            case AUTH_CM_VOID:
//                return "1".equals(repository.getParamValue(PreferencesConstants.PRE_AUTH_COMPLETE_VOID_NEED_PASSWORD));
//            case AUTH_CM:
//            case AUTH_CM_NOTIFY:
//                return "1".equals(repository.getParamValue(PreferencesConstants.PRE_AUTH_COMPLETE_NEED_PASSWORD));
//            default:
//                return true;
//        }
//    }

    /**
     * 判断交易是否能走免密
     *
     * @param pan          卡号
     * @param amount       金额
     * @param isCreditCard 是否贷记卡
     * @param repository   仓库
     * @return
     */
//    public static boolean isQpsFreePin(String pan, String amount, boolean isCreditCard, DataSource repository) {
//        boolean isFreePin = false;
//        if ("1".equals(repository.getParamValue(PreferencesConstants.QPS_SUPPORT_FREE_PASSWORD))) {
//            if ("1".equals(repository.getParamValue(PreferencesConstants.QPS_SUPPORT_CARD_BIN_A))) {
//                isFreePin = repository.isInQpsBinA(pan);
//            } else {
//                if ("1".equals(repository.getParamValue(PreferencesConstants.QPS_SUPPORT_CARD_BIN_B))) {
//                    isFreePin = isCreditCard || repository.isInQpsBinB(pan);
//                } else {
//                    if ("1".equals(repository.getParamValue(PreferencesConstants.QPS_SUPPORT_CARD_BIN_C))) {
//                        if (!repository.isInQpsBinC(pan)) {
//                            isFreePin = true;
//                        }
//                    } else {
//                        isFreePin = true;
//                    }
//                }
//            }
//        }
//        if (isFreePin) {
//            if (Double.parseDouble(StringUtils.formatAmount(amount, SysConstant.FUND_DIGITS))
//                    <= Double.parseDouble(StringUtils.formatAmount(repository.getParamValue(PreferencesConstants.QPS_LIMIT_OF_FREE_PASSWORD), SysConstant.FUND_DIGITS))) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * 判断交易是否可以免签
     *
     * @param amount     金额
     * @param repository 仓库
     * @return
     */
//    public static boolean isQpsFreeSignature(String amount, DataSource repository) {
//        if ("1".equals(repository.getParamValue(PreferencesConstants.QPS_SUPPORT_FREE_SIGNATURE))) {
//            if (Double.valueOf(StringUtils.formatAmount(amount, SysConstant.FUND_DIGITS))
//                    <= Double.valueOf(StringUtils.formatAmount(repository.getParamValue(PreferencesConstants.QPS_LIMIT_OF_FREE_SIGNATURE), SysConstant.FUND_DIGITS))) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * 判断交易是否需求发起电子签名
     *
     * @param isEcFlow   是否电子现金流程
     * @param repository 仓库
     * @return
     */
//    public static boolean isNeedElectronicSignature(boolean isEcFlow, DataSource repository) {
//        if (isEcFlow) {
//            //电子现金脱机
//            //判断电子现金是否免签
//            if ("1".equals(repository.getParamValue(PreferencesConstants.EC_FREE_SIGNATURE))) {
//                return false;
//            }
//        }
//        //判断是否开启电子签名
//        if (!"1".equals(repository.getParamValue(PreferencesConstants.IS_SUPPORT_ELEC_SIGNATURE))) {
//            return false;
//        }
//        return true;
//    }
}
