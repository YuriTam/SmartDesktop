package com.smart.desktop.core.api;

import android.content.Context;

import com.smart.desktop.core.bean.UserInfo;

import java.util.List;

/**
 * 数据操作接口
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年02月02日
 */
public interface IDataSource {

    /**
     * 初始化相关数据
     */
    void initDataSource(Context context);

    /************************ 配置文件相关 *************************/

    /**
     * 保存数据到map内存表
     *
     * @param key   键值
     * @param value 数据
     */
    void saveParamValue(String key, String value);

    /**
     * 获取map内存表数据
     *
     * @param key 键值
     * @param defValue 默认数据
     * @return 数据
     */
    String getParamValue(String key, String defValue);

    /**
     * 同步更新数据，即写入到文件中
     */
    void syncParamValue();

    /************************ 用户表相关 *************************/

    /**
     * 保存用户信息
     *
     * @param info
     */
    void saveUser(UserInfo info);

    /**
     * 查询用户信息
     *
     * @param userNo 操作员编号
     * @return
     */
    UserInfo getUserInfo(String userNo);

    /**
     * 获取所有操作员
     *
     * @return
     */
    List<UserInfo> getUserList();

    /**
     * 更新用户信息
     *
     * @param info
     */
    void updateUserInfo(UserInfo info);

    /**
     * 删除用户信息
     *
     * @param userNo
     */
    void deleteByUserId(String userNo);

    /**
     * 删除所有用户信息
     */
    void deleteAllUserInfo();
}
