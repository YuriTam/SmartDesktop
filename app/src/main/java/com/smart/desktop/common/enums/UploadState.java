package com.smart.desktop.common.enums;

/**
 * 离线上送状态
 */
public enum UploadState {
    /**
     * 未上送
     */
    NONE,
    /**
     * 离线上送成功
     */
    OFFLINE_UPLOAD_SUCCESSFUL,
    /**
     * 离线上送失败
     */
    OFFLINE_UPLOAD_FAILED,
    /**
     * 离线上送被拒
     */
    OFFLINE_UPLOAD_REJECTED,
}
