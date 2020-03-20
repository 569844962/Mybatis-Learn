package com.chen.mybatis.demo3.pojo;

import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.3.1
 * @company
 */
public class BlogDiscriminator {
    private Integer blogId;

    private String name;

    private String book;

    private String isPay;

    private Pay pay;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BlogDiscriminator.class.getSimpleName() + "[", "]")
                .add("blogId=" + blogId)
                .add("name='" + name + "'")
                .add("book='" + book + "'")
                .add("isPay='" + isPay + "'")
                .add("pay=" + pay)
                .toString();
    }
}
