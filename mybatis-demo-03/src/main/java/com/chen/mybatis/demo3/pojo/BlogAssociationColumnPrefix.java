package com.chen.mybatis.demo3.pojo;

import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.2.27
 * @company
 */
public class BlogAssociationColumnPrefix {

    private Integer id;
    private String name;
    private String book;

    private User user;

    private User secondUser;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BlogAssociationColumnPrefix.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("book='" + book + "'")
                .add("user=" + user)
                .add("secondUser=" + secondUser)
                .toString();
    }
}
