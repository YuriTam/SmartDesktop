package com.smart.desktop.core.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * 用户信息实体类
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年02月02日
 */
@Entity(nameInDb = "User")
public class UserInfo implements Serializable {

    static final long serialVersionUID = 42L;

    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "USER_NO")
    private String userNo;  //操作员编号
    @Property(nameInDb = "PASSWORD")
    private String password;//密码

    public UserInfo(String userNo, String password) {
        this.userNo = userNo;
        this.password = password;
    }

    @Generated(hash = 621694042)
    public UserInfo(Long id, String userNo, String password) {
        this.id = id;
        this.userNo = userNo;
        this.password = password;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserNo() {
        return this.userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
