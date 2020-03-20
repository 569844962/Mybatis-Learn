package com.chen.mybatis.demo3.pojo;

import java.util.List;
import java.util.StringJoiner;

/**
 * 〈Blog 关联〉
 *
 * @author chenmingjun
 * @create 2020.2.25
 * @company
 */
public class BlogAssociation {

    private Integer id;

    private String name;

    private String book;

    private User user;

    private BlogMessageList blogMessage;

    private List<BlogMessage> blogMessageList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BlogMessage> getBlogMessageList() {
        return blogMessageList;
    }

    public void setBlogMessageList(List<BlogMessage> blogMessageList) {
        this.blogMessageList = blogMessageList;
    }

    public BlogMessageList getBlogMessage() {
        return blogMessage;
    }

    public void setBlogMessage(BlogMessageList blogMessage) {
        this.blogMessage = blogMessage;
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
        return new StringJoiner(", ", BlogAssociation.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("book='" + book + "'")
                .add("user=" + user)
                .add("blogMessage=" + blogMessage)
                .add("blogMessageList=" + blogMessageList)
                .toString();
    }
}
