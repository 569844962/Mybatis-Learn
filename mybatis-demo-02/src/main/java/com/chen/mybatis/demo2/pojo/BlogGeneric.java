package com.chen.mybatis.demo2.pojo;

import com.chen.mybatis.demo2.emuns.BlogGenericeTypeEnum;

import java.util.Date;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.1.18
 * @company
 */
public class BlogGeneric {
    private Long id;

    private String name;

    private Date createTime;

    private BlogGenericeTypeEnum blogType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BlogGenericeTypeEnum getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogGenericeTypeEnum blogType) {
        this.blogType = blogType;
    }

    @Override
    public String toString() {
        return "BlogGeneric{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", blogType=" + blogType +
                '}';
    }
}
