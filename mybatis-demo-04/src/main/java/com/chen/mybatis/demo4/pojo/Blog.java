package com.chen.mybatis.demo4.pojo;

import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.3.2
 * @company
 */
public class Blog {

    private Integer id;

    private String name;

    private String book;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                .toString();
    }
}
