package com.chen.mybatis.demo3.pojo;

import java.util.Date;
import java.util.StringJoiner;

/**
 * 〈用户〉
 *
 * @author chenmingjun
 * @create 2020.2.24
 * @company
 */
public class User {

    private Integer userId;

    private String userName;

    //用于测试自动映射配置（PARTIAL -- FULL ）
    private String name;

    private String mobile;

    private Date createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("userName='" + userName + "'")
                .add("name='" + name + "'")
                .add("mobile='" + mobile + "'")
                .add("createTime=" + createTime)
                .toString();
    }
}
