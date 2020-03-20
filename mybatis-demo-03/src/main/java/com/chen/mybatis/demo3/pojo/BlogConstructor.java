package com.chen.mybatis.demo3.pojo;

import org.apache.ibatis.annotations.Param;

import java.util.StringJoiner;

/**
 * 〈Blog 构造函数〉
 *
 * @author chenmingjun
 * @create 2020.2.25
 * @company
 */
public class BlogConstructor {

    private Integer id;

    private String name;

    private String book;

    public BlogConstructor(@Param("id") Integer id,@Param("name") String name,@Param("book") String book) {
        this.id = id;
        this.name = name;
        this.book = book;
    }


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
        return new StringJoiner(", ", BlogConstructor.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("book='" + book + "'")
                .toString();
    }
}
