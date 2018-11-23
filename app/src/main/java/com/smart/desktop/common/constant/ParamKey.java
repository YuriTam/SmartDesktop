package com.smart.desktop.common.constant;

/**
 * 参数键
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年02月07日
 */
public class ParamKey {

    //记录当前的操作员编号，操作员列表存储在数据库中
    public static final String CURRENT_USER_NO = "current_user_no";
    public static final String LOGIN_DATE = "login_date";
    //商户信息管理相关
    public static final String TERMINAL_ID = "terminal_id";
    public static final String MERCHANT_ID = "merchant_id";
    public static final String MERCHANT_NAME = "merchant_name";
    public static final String MERCHANT_USER_NAME = "merchant_user_name";
    public static final String BANK_ACCOUNT_NO = "bank_account_no";
    public static final String MERCHANT_ADDRESS = "merchant_address";
    //传统类交易开关控制
    public static final String SALE_SWITCH = "sale_switch";
    public static final String SALE_VOID_SWITCH = "sale_void_switch";
    public static final String REFUND_SWITCH = "refund_switch";
    public static final String QUERY_SWITCH = "query_switch";
    public static final String PRE_AUTH_SWITCH = "pre_auth_switch";
    public static final String PRE_AUTH_VOID_SWITCH = "pre_auth_void_switch";
    public static final String PRE_AUTH_COMPLETE_SWITCH = "pre_auth_complete_switch";
    public static final String PRE_AUTH_COMPLETE_VOID_SWITCH = "pre_auth_complete_void_switch";
    public static final String PRE_AUTH_COMPLETE_NOTIFY_SWITCH = "pre_auth_complete_notify_switch";
    //电子签名设置相关
    public static final String IS_SUPPORT_ELEC_SIGNATURE = "is_support_elec_signature";
    public static final String IS_SHOW_CONFIRM_SIGNATURE = "is_show_confirm_signature";
    public static final String SIGNATURE_TIME_OUT = "signature_time_out";
    public static final String SIGNATURE_UPLOAD_TIMES = "signature_upload_times";
    public static final String MAX_SIGNATURE_TIMES = "max_signature_times";
    public static final String IS_NEED_PHONE_NUMBER = "is_need_phone_number";
    public static final String IS_PRINT_SALES_SLIP = "is_print_sales_slip";
    //打印相关
    public static final String PRINT_TIMES = "print_times";
    //结算相关
    public static final String IS_SETTLE_CUEENT_DATE = "is_settle_current_date";
    //服务热线
    public static final String SERVICE_HPONE = "service_phone";
}
