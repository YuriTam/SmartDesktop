package com.smart.desktop.common.enums;

/**
 * 操作员类型
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月23日
 */
public enum UserType {

    SUPERVISOR("00"), //主管操作员

    ADMIN("99"), //系统管理员

    OTHER("");  //普通操作员

    private String userNo;

    UserType(String userNo) {
        this.userNo = userNo;
    }

    public String getUserNo() {
        return userNo;
    }

    /**
     * 获取操作员类型
     *
     * @param userNo
     * @return
     */
    public static UserType getUserType(String userNo){
        if (SUPERVISOR.getUserNo().equals(userNo)) return SUPERVISOR;
        if (ADMIN.getUserNo().equals(userNo)) return ADMIN;
        return OTHER;
    }

}
