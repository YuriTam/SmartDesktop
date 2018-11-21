package com.smart.desktop.common.enums;

/**
 * 交易类型及相应信息枚举
 * Created by xiaox on 2017/1/27.
 */
public enum TransType {

    TRANS_TEST(0, 0, "0820", "", "", "00", false, false, false),

    UPLOAD_STATE(0, 0, "0820", "", "", "00", false, false, false),

    RF_PARAM_DOWN(0, 0, "0800", "", "", "00", false, false, false),

    QPS_BIN_B_DOWN(0, 0, "0800", "", "", "00", false, false, false),

    QPS_BIN_B_END(0, 0, "0800", "", "", "00", false, false, false),

    QPS_BIN_C_DOWN(0, 0, "0800", "", "", "00", false, false, false),

    QPS_BIN_C_END(0, 0, "0800", "", "", "00", false, false, false),

    LOGIN(0, 0, "0800", "", "", "00", false, false, false),

    LOGOUT(0, 0, "0820", "", "", "00", false, false, false),

    CAPK_NOTIFY(0, 0, "0820", "", "", "00", false, false, false),

    CAPK_DOWN(0, 0, "0800", "", "", "00", false, false, false),

    CAPK_END(0, 0, "0800", "", "", "00", false, false, false),

    AID_NOTIFY(0, 0, "0820", "", "", "00", false, false, false),

    AID_DOWN(0, 0, "0800", "", "", "00", false, false, false),

    AID_END(0, 0, "0800", "", "", "00", false, false, false),

    PARAM_DOWN(0, 0, "0800", "", "", "00", false, false, false),

    BLACKLIST_DOWN(0, 0, "0800", "", "", "00", false, false, false),

    BLACKLIST_END(0, 0, "0800", "", "", "00", false, false, false),

    SCRIPT_NOTIFY(0, 0, "0620", "", "", "00", false, false, false),

    REVERSAL(0, 0, "0400", "", "", "00", false, false, false),

    SALE(0, 0, "0200", "000000", "00", "22", true, true, true),

    OFF_SALE(0, 0, "0200", "000000", "00", "36", true, true, true),

    SCAN_SALE(0, 0, "0200", "000000", "00", "22", true, true, true),

    VOID(0, 0, "0200", "200000", "00", "23", true, true, true),

    SCAN_VOID(0, 0, "0200", "200000", "00", "23", true, true, true),

    REFUND(0, 0, "0220", "200000", "00", "25", true, false, true),

    SCAN_REFUND(0, 0, "0220", "200000", "00", "25", true, false, true),

    QUERY(0, 0, "0200", "310000", "00", "01", false, false, true),

    AUTH(0, 0, "0100", "030000", "06", "10", true, true, true),

    AUTH_VOID(0, 0, "0100", "200000", "06", "11", true, true, true),

    AUTH_CM(0, 0, "0200", "000000", "06", "20", true, true, true),

    AUTH_CM_VOID(0, 0, "0200", "200000", "06", "21", true, true, true),

    AUTH_CM_NOTIFY(0, 0, "0220", "000000", "06", "24", true, false, true),

    EC_SALE(0, 0, "0200", "000000", "00", "36", true, true, false),

    EC_LOG(0, 0, "0200", "000000", "00", "", false, false, false),

    EC_BALANCE(0, 0, "0200", "000000", "00", "", false, false, false),

    SETTLE(0, 0, "0500", "", "", "00", false, false, true),

    UPLOAD_MAG_OFFLINE(0, 0, "0220", "", "", "", false, false, false),

    UPLOAD_ICC_OFFLINE(0, 0, "0200", "", "", "", false, false, false),

    UPLOAD_ES(0, 0, "0820", "", "", "07", false, false, false),

    BATCH_UPLOAD_ICC_OFFLINE(0, 0, "0320", "", "", "", false, false, false),

    BATCH_UPLOAD_ICC_ONLINE(0, 0, "0320", "", "", "", false, false, false),

    BATCH_UPLOAD_ICC_ONLINE_ARPC(0, 0, "0320", "", "", "", false, false, false),

    BATCH_UPLOAD_ICC_OFFLINE_DECLINED(0, 0, "0320", "", "", "", false, false, false),

    BATCH_UPLOAD_ICC_NOTIFY(0, 0, "0320", "", "", "", false, false, false),

    BATCH_UPLOAD_MAG_NOTIFY(0, 0, "0320", "", "", "", false, false, false),

    BATCH_UPLOAD_MAG_ONLINE(0, 0, "0320", "", "", "", false, false, false),

    BATCH_UPLOAD_MAG_OFFLINE(0, 0, "0320", "", "", "", false, false, false),

    BATCH_END(0, 0, "0320", "", "", "00", false, false, true);
    /**
     * 交易中文名称
     */
    private final int name;
    /**
     * 交易英文名称
     */
    private final int enName;
    /**
     * 消息类型
     */
    private final String msgType;
    /**
     * 交易处理码3域
     */
    private final String procCode;
    /**
     * 服务点条件码25域
     */
    private final String conditionCode;
    /**
     * 消息类型码60.1域
     */
    private final String funcCode;
    /**
     * 交易是否存数据库
     */
    private boolean saveRecord;
    /**
     * 交易异常时是否需要冲正
     */
    private boolean reversal;
    /**
     * 交易发送前是否需要发提前包（脚本上送，冲正，电子签名等等）
     */
    private boolean preSend;

    TransType(int name, int enName, String msgType, String procCode, String conditionCode, String funcCode, boolean saveRecord, boolean reversal, boolean preSend) {
        this.name = name;
        this.enName = enName;
        this.msgType = msgType;
        this.procCode = procCode;
        this.conditionCode = conditionCode;
        this.funcCode = funcCode;
        this.saveRecord = saveRecord;
        this.reversal = reversal;
        this.preSend = preSend;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getProcCode() {
        return procCode;
    }

    public String getFuncCode() {
        return funcCode;
    }

    public int getName() {
        return name;
    }

    public int getEnName() {
        return enName;
    }

    public boolean isSaveRecord() {
        return saveRecord;
    }

    public boolean isPreSend() {
        return preSend;
    }

    public boolean isReversal() {
        return reversal;
    }

    public String getConditionCode() {
        return conditionCode;
    }
}
