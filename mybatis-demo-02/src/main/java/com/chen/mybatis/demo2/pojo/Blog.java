package com.chen.mybatis.demo2.pojo;

import com.chen.mybatis.demo2.emuns.BlogTypeEnum;

import java.util.Date;
import java.util.HashSet;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2019.12.20
 * @company
 */
public class Blog {

    private Long id;

    private String name;

    private Date createTime;

    private HashSet books;

    private BlogTypeEnum blogType;

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

    public HashSet getBooks() {
        return books;
    }

    public void setBooks(HashSet books) {
        this.books = books;
    }

    public BlogTypeEnum getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogTypeEnum blogType) {
        this.blogType = blogType;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", books=" + books +
                ", blogType=" + blogType +
                '}';
    }
}