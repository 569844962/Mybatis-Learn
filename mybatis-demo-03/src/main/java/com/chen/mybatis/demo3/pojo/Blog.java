package com.chen.mybatis.demo3.pojo;

import java.util.Date;
import java.util.StringJoiner;

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

    private String book;

    private Date createTime;


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


    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Blog.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("book='" + book + "'")
                .add("createTime=" + createTime)
                .toString();
    }
}