package com.smart.desktop.core.bean;

import com.smart.desktop.common.enums.CardMode;
import com.smart.desktop.common.enums.OfflineState;
import com.smart.desktop.common.enums.TransType;
import com.smart.desktop.common.enums.UploadState;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * 交易记录表
 */
@Entity(nameInDb = "TRANS_RECORD")
public class TransRecord {
    @Id(autoincrement = true)
    private Long id;
    /**
     * 交易类型 {@link TransType}
     */
    @Property(nameInDb = "TRANS_TYPE")
    private int transType;
    /**
     * 操作员号
     */
    @Property(nameInDb = "OPERATOR")
    private int operator;
    /**
     * 用卡方式 {@link CardMode}
     */
    @Property(nameInDb = "CARD_MODE")
    private int cardMode;
    /**
     * 卡号
     */
    @Property(nameInDb = "PAN")
    private String pan;
    /**
     * 支付码（扫码相关）
     */
    @Property(nameInDb = "PAY_CODE")
    private String payCode;
    /**
     * 订单号
     */
    @Property(nameInDb = "ORDER_NO")
    private String orderNo;
    /**
     * 交易金额，12位表示
     */
    @Property(nameInDb = "AMOUNT")
    private String amount;
    /**
     * 流水号
     */
    @Property(nameInDb = "TRACE_NUM")
    private String traceNum;
    /**
     * 交易时间
     */
    @Property(nameInDb = "TIME")
    private String time;
    /**
     * 交易日期
     */
    @Property(nameInDb = "DATE")
    private String date;
    /**
     * 卡有效期
     */
    @Property(nameInDb = "EXP_DATE")
    private String expDate;
    /**
     * 结算日期
     */
    @Property(nameInDb = "SETTLE_DATE")
    private String settleDate;
    /**
     * 输入方式
     */
    @Property(nameInDb = "ENTRY_MODE")
    private String entryMode;
    /**
     * 卡序列号
     */
    @Property(nameInDb = "CSN")
    private String csn;
    /**
     * 受理机构标识码
     */
    @Property(nameInDb = "ACCEPT_CODE")
    private String acceptCode;
    /**
     * 系统参考号
     */
    @Property(nameInDb = "REF_NUM")
    private String refNum;
    /**
     * 授权码
     */
    @Property(nameInDb = "AUTH_CODE")
    private String authCode;
    /**
     * 响应码
     */
    @Property(nameInDb = "RESP_CODE")
    private String respCode;
    /**
     * 终端号
     */
    @Property(nameInDb = "TER_ID")
    private String terminalId;
    /**
     * 商户名称
     */
    @Property(nameInDb = "MER_NAME")
    private String merchantName;
    /**
     * 商户号
     */
    @Property(nameInDb = "MER_ID")
    private String merchantId;
    /**
     * 货币代码
     */
    @Property(nameInDb = "FUND_CODE")
    private String fundType;
    /**
     * 余额，后台有返回时保存
     */
    @Property(nameInDb = "BALANCE")
    private String balance;
    /**
     * EMV数据，55域
     */
    @Property(nameInDb = "EMV_TLV")
    private String emvTlv;
    /**
     * 脚本数据
     */
    @Property(nameInDb = "SCRIPT_TLV")
    private String scriptTlv;
    /**
     * 冲正EMV数据
     */
    @Property(nameInDb = "REVERSAL_TLV")
    private String reversalTlv;
    /**
     * 交易完成EMV后续需要的数据，如打印单等
     */
    @Property(nameInDb = "RESULT_TLV")
    private String resultTlv;
    /**
     * 批次号
     */
    @Property(nameInDb = "BATCH_NUM")
    private String batchNum;
    /**
     * 原流水号
     */
    @Property(nameInDb = "ORG_TRACE_NUM")
    private String orgTraceNum;
    /**
     * 原批次号
     */
    @Property(nameInDb = "ORG_BATCH_NUM")
    private String orgBatchNum;
    /**
     * 原交易日期
     */
    @Property(nameInDb = "ORG_DATE")
    private String orgDate;
    /**
     * 原授权码
     */
    @Property(nameInDb = "ORG_AUTH_CODE")
    private String orgAuthCode;
    /**
     * 原系统参考号
     */
    @Property(nameInDb = "ORG_REF_NUM")
    private String orgRefNum;
    /**
     * 国际组织代码
     */
    @Property(nameInDb = "INTER_ORG_CODE")
    private String interOrgCode;
    /**
     * 发卡机构标识码
     */
    @Property(nameInDb = "ISS_CODE")
    private String issCode;
    /**
     * 收单机构标识码
     */
    @Property(nameInDb = "ACQ_CODE")
    private String acqCode;
    /**
     * 发卡机构返回的信息
     */
    @Property(nameInDb = "ISS_INFO")
    private String issInfo;
    /**
     * 银联返回的信息
     */
    @Property(nameInDb = "CUP_INFO")
    private String cupInfo;
    /**
     * 受理机构返回的信息
     */
    @Property(nameInDb = "MER_ACQ_INFO")
    private String merAcqInfo;
    /**
     * POS 终端返回的信息
     */
    @Property(nameInDb = "POS_TER_INFO")
    private String posTerInfo;
    /**
     * 后台返回的营销信息
     */
    @Property(nameInDb = "MARKET_INFO")
    private String marketInfo;
    /**
     * 持卡人姓名
     */
    @Property(nameInDb = "CARD_HOLDER")
    private String cardHolder;
    /**
     * 脱机状态 {@link OfflineState}
     */
    @Property(nameInDb = "OFFLINE_STATE")
    private int offlineState;
    /**
     * 离线上送状态 {@link UploadState}
     */
    @Property(nameInDb = "UPLOAD_STATE")
    private int uploadState;
    /**
     * 是否arpc错
     */
    @Property(nameInDb = "IS_ARPC_ERR")
    private boolean isArpcErr;
    /**
     * 是否通知类交易
     */
    @Property(nameInDb = "IS_NOTIFY")
    private boolean isNotify;
    /**
     * 是否已被撤销
     */
    @Property(nameInDb = "IS_VOID")
    private boolean isVoid;
    /**
     * 交易是否成功
     */
    @Property(nameInDb = "IS_SUCCESS")
    private boolean isSuccess;
    /**
     * 是否qps免密
     */
    @Property(nameInDb = "IS_FREE_PIN")
    private boolean isQpsFreePin;
    /**
     * qps免密金额限额
     */
    @Property(nameInDb = "FREE_PIN_AMOUNT_LIMIT")
    private String qpsFreePinAmountLimit;
    /**
     * 是否cdcvm免密免签
     */
    @Property(nameInDb = "IS_CDCVM")
    private boolean isCdcvm;
    /**
     * 是否qps免签
     */
    @Property(nameInDb = "IS_FREE_SIGN")
    private boolean isQpsFreeSignature;
    /**
     * qps免签金额限额
     */
    @Property(nameInDb = "FREE_SIGN_AMOUNT_LIMIT")
    private String qpsFreeSignatureAmountLimit;
    /**
     * 是否IC卡降级
     */
    @Property(nameInDb = "IS_FALLBACK")
    private boolean isFallback;
    /**
     * 是否需要冲正
     */
    @Property(nameInDb = "IS_NEED_REVERSAL")
    private boolean isNeedReversal;
    /**
     * 是否外卡
     */
    @Property(nameInDb = "IS_INTERNATIONAL")
    private boolean isInternational;
    /**
     * 批上送是否成功
     */
    @Property(nameInDb = "IS_BATCH_UPLOAD")
    private boolean isBatchUpload;
    /**
     * 交易是否已经打印
     */
    @Property(nameInDb = "IS_PRINTED")
    private boolean isPrinted;
    /**
     * 冲正原因码
     */
    @Property(nameInDb = "REVERSAL_CODE")
    private String reversalCode;
    @Generated(hash = 720268237)
    public TransRecord(Long id, int transType, int operator, int cardMode,
            String pan, String payCode, String orderNo, String amount,
            String traceNum, String time, String date, String expDate,
            String settleDate, String entryMode, String csn, String acceptCode,
            String refNum, String authCode, String respCode, String terminalId,
            String merchantName, String merchantId, String fundType, String balance,
            String emvTlv, String scriptTlv, String reversalTlv, String resultTlv,
            String batchNum, String orgTraceNum, String orgBatchNum, String orgDate,
            String orgAuthCode, String orgRefNum, String interOrgCode,
            String issCode, String acqCode, String issInfo, String cupInfo,
            String merAcqInfo, String posTerInfo, String marketInfo,
            String cardHolder, int offlineState, int uploadState, boolean isArpcErr,
            boolean isNotify, boolean isVoid, boolean isSuccess,
            boolean isQpsFreePin, String qpsFreePinAmountLimit, boolean isCdcvm,
            boolean isQpsFreeSignature, String qpsFreeSignatureAmountLimit,
            boolean isFallback, boolean isNeedReversal, boolean isInternational,
            boolean isBatchUpload, boolean isPrinted, String reversalCode) {
        this.id = id;
        this.transType = transType;
        this.operator = operator;
        this.cardMode = cardMode;
        this.pan = pan;
        this.payCode = payCode;
        this.orderNo = orderNo;
        this.amount = amount;
        this.traceNum = traceNum;
        this.time = time;
        this.date = date;
        this.expDate = expDate;
        this.settleDate = settleDate;
        this.entryMode = entryMode;
        this.csn = csn;
        this.acceptCode = acceptCode;
        this.refNum = refNum;
        this.authCode = authCode;
        this.respCode = respCode;
        this.terminalId = terminalId;
        this.merchantName = merchantName;
        this.merchantId = merchantId;
        this.fundType = fundType;
        this.balance = balance;
        this.emvTlv = emvTlv;
        this.scriptTlv = scriptTlv;
        this.reversalTlv = reversalTlv;
        this.resultTlv = resultTlv;
        this.batchNum = batchNum;
        this.orgTraceNum = orgTraceNum;
        this.orgBatchNum = orgBatchNum;
        this.orgDate = orgDate;
        this.orgAuthCode = orgAuthCode;
        this.orgRefNum = orgRefNum;
        this.interOrgCode = interOrgCode;
        this.issCode = issCode;
        this.acqCode = acqCode;
        this.issInfo = issInfo;
        this.cupInfo = cupInfo;
        this.merAcqInfo = merAcqInfo;
        this.posTerInfo = posTerInfo;
        this.marketInfo = marketInfo;
        this.cardHolder = cardHolder;
        this.offlineState = offlineState;
        this.uploadState = uploadState;
        this.isArpcErr = isArpcErr;
        this.isNotify = isNotify;
        this.isVoid = isVoid;
        this.isSuccess = isSuccess;
        this.isQpsFreePin = isQpsFreePin;
        this.qpsFreePinAmountLimit = qpsFreePinAmountLimit;
        this.isCdcvm = isCdcvm;
        this.isQpsFreeSignature = isQpsFreeSignature;
        this.qpsFreeSignatureAmountLimit = qpsFreeSignatureAmountLimit;
        this.isFallback = isFallback;
        this.isNeedReversal = isNeedReversal;
        this.isInternational = isInternational;
        this.isBatchUpload = isBatchUpload;
        this.isPrinted = isPrinted;
        this.reversalCode = reversalCode;
    }
    @Generated(hash = 230419376)
    public TransRecord() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getTransType() {
        return this.transType;
    }
    public void setTransType(int transType) {
        this.transType = transType;
    }
    public int getOperator() {
        return this.operator;
    }
    public void setOperator(int operator) {
        this.operator = operator;
    }
    public int getCardMode() {
        return this.cardMode;
    }
    public void setCardMode(int cardMode) {
        this.cardMode = cardMode;
    }
    public String getPan() {
        return this.pan;
    }
    public void setPan(String pan) {
        this.pan = pan;
    }
    public String getPayCode() {
        return this.payCode;
    }
    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }
    public String getOrderNo() {
        return this.orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getAmount() {
        return this.amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getTraceNum() {
        return this.traceNum;
    }
    public void setTraceNum(String traceNum) {
        this.traceNum = traceNum;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getExpDate() {
        return this.expDate;
    }
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
    public String getSettleDate() {
        return this.settleDate;
    }
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }
    public String getEntryMode() {
        return this.entryMode;
    }
    public void setEntryMode(String entryMode) {
        this.entryMode = entryMode;
    }
    public String getCsn() {
        return this.csn;
    }
    public void setCsn(String csn) {
        this.csn = csn;
    }
    public String getAcceptCode() {
        return this.acceptCode;
    }
    public void setAcceptCode(String acceptCode) {
        this.acceptCode = acceptCode;
    }
    public String getRefNum() {
        return this.refNum;
    }
    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }
    public String getAuthCode() {
        return this.authCode;
    }
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    public String getRespCode() {
        return this.respCode;
    }
    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
    public String getTerminalId() {
        return this.terminalId;
    }
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
    public String getMerchantName() {
        return this.merchantName;
    }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public String getMerchantId() {
        return this.merchantId;
    }
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    public String getFundType() {
        return this.fundType;
    }
    public void setFundType(String fundType) {
        this.fundType = fundType;
    }
    public String getBalance() {
        return this.balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getEmvTlv() {
        return this.emvTlv;
    }
    public void setEmvTlv(String emvTlv) {
        this.emvTlv = emvTlv;
    }
    public String getScriptTlv() {
        return this.scriptTlv;
    }
    public void setScriptTlv(String scriptTlv) {
        this.scriptTlv = scriptTlv;
    }
    public String getReversalTlv() {
        return this.reversalTlv;
    }
    public void setReversalTlv(String reversalTlv) {
        this.reversalTlv = reversalTlv;
    }
    public String getResultTlv() {
        return this.resultTlv;
    }
    public void setResultTlv(String resultTlv) {
        this.resultTlv = resultTlv;
    }
    public String getBatchNum() {
        return this.batchNum;
    }
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
    public String getOrgTraceNum() {
        return this.orgTraceNum;
    }
    public void setOrgTraceNum(String orgTraceNum) {
        this.orgTraceNum = orgTraceNum;
    }
    public String getOrgBatchNum() {
        return this.orgBatchNum;
    }
    public void setOrgBatchNum(String orgBatchNum) {
        this.orgBatchNum = orgBatchNum;
    }
    public String getOrgDate() {
        return this.orgDate;
    }
    public void setOrgDate(String orgDate) {
        this.orgDate = orgDate;
    }
    public String getOrgAuthCode() {
        return this.orgAuthCode;
    }
    public void setOrgAuthCode(String orgAuthCode) {
        this.orgAuthCode = orgAuthCode;
    }
    public String getOrgRefNum() {
        return this.orgRefNum;
    }
    public void setOrgRefNum(String orgRefNum) {
        this.orgRefNum = orgRefNum;
    }
    public String getInterOrgCode() {
        return this.interOrgCode;
    }
    public void setInterOrgCode(String interOrgCode) {
        this.interOrgCode = interOrgCode;
    }
    public String getIssCode() {
        return this.issCode;
    }
    public void setIssCode(String issCode) {
        this.issCode = issCode;
    }
    public String getAcqCode() {
        return this.acqCode;
    }
    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }
    public String getIssInfo() {
        return this.issInfo;
    }
    public void setIssInfo(String issInfo) {
        this.issInfo = issInfo;
    }
    public String getCupInfo() {
        return this.cupInfo;
    }
    public void setCupInfo(String cupInfo) {
        this.cupInfo = cupInfo;
    }
    public String getMerAcqInfo() {
        return this.merAcqInfo;
    }
    public void setMerAcqInfo(String merAcqInfo) {
        this.merAcqInfo = merAcqInfo;
    }
    public String getPosTerInfo() {
        return this.posTerInfo;
    }
    public void setPosTerInfo(String posTerInfo) {
        this.posTerInfo = posTerInfo;
    }
    public String getMarketInfo() {
        return this.marketInfo;
    }
    public void setMarketInfo(String marketInfo) {
        this.marketInfo = marketInfo;
    }
    public String getCardHolder() {
        return this.cardHolder;
    }
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }
    public int getOfflineState() {
        return this.offlineState;
    }
    public void setOfflineState(int offlineState) {
        this.offlineState = offlineState;
    }
    public int getUploadState() {
        return this.uploadState;
    }
    public void setUploadState(int uploadState) {
        this.uploadState = uploadState;
    }
    public boolean getIsArpcErr() {
        return this.isArpcErr;
    }
    public void setIsArpcErr(boolean isArpcErr) {
        this.isArpcErr = isArpcErr;
    }
    public boolean getIsNotify() {
        return this.isNotify;
    }
    public void setIsNotify(boolean isNotify) {
        this.isNotify = isNotify;
    }
    public boolean getIsVoid() {
        return this.isVoid;
    }
    public void setIsVoid(boolean isVoid) {
        this.isVoid = isVoid;
    }
    public boolean getIsSuccess() {
        return this.isSuccess;
    }
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    public boolean getIsQpsFreePin() {
        return this.isQpsFreePin;
    }
    public void setIsQpsFreePin(boolean isQpsFreePin) {
        this.isQpsFreePin = isQpsFreePin;
    }
    public String getQpsFreePinAmountLimit() {
        return this.qpsFreePinAmountLimit;
    }
    public void setQpsFreePinAmountLimit(String qpsFreePinAmountLimit) {
        this.qpsFreePinAmountLimit = qpsFreePinAmountLimit;
    }
    public boolean getIsCdcvm() {
        return this.isCdcvm;
    }
    public void setIsCdcvm(boolean isCdcvm) {
        this.isCdcvm = isCdcvm;
    }
    public boolean getIsQpsFreeSignature() {
        return this.isQpsFreeSignature;
    }
    public void setIsQpsFreeSignature(boolean isQpsFreeSignature) {
        this.isQpsFreeSignature = isQpsFreeSignature;
    }
    public String getQpsFreeSignatureAmountLimit() {
        return this.qpsFreeSignatureAmountLimit;
    }
    public void setQpsFreeSignatureAmountLimit(String qpsFreeSignatureAmountLimit) {
        this.qpsFreeSignatureAmountLimit = qpsFreeSignatureAmountLimit;
    }
    public boolean getIsFallback() {
        return this.isFallback;
    }
    public void setIsFallback(boolean isFallback) {
        this.isFallback = isFallback;
    }
    public boolean getIsNeedReversal() {
        return this.isNeedReversal;
    }
    public void setIsNeedReversal(boolean isNeedReversal) {
        this.isNeedReversal = isNeedReversal;
    }
    public boolean getIsInternational() {
        return this.isInternational;
    }
    public void setIsInternational(boolean isInternational) {
        this.isInternational = isInternational;
    }
    public boolean getIsBatchUpload() {
        return this.isBatchUpload;
    }
    public void setIsBatchUpload(boolean isBatchUpload) {
        this.isBatchUpload = isBatchUpload;
    }
    public boolean getIsPrinted() {
        return this.isPrinted;
    }
    public void setIsPrinted(boolean isPrinted) {
        this.isPrinted = isPrinted;
    }
    public String getReversalCode() {
        return this.reversalCode;
    }
    public void setReversalCode(String reversalCode) {
        this.reversalCode = reversalCode;
    }
    
}
