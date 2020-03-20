package com.chen.mybatis.demo3.pojo;

import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

/**
 * 〈Blog info〉
 *
 * @author chenmingjun
 * @create 2020.2.25
 * @company
 */
public class BlogInfo {

    private Integer blogId;

    private String name;

    private String book;

    private Blog blog;

    private Date createTime;

    private BlogInfoDb blogInfoDb;

    private User user;
    private List<BlogMessage> blogMessages;

    public BlogInfo() {
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }


    public BlogInfoDb getBlogInfoDb() {
        return blogInfoDb;
    }

    public void setBlogInfoDb(BlogInfoDb blogInfoDb) {
        this.blogInfoDb = blogInfoDb;
    }

    public BlogInfo(Integer blogId) {
        this.blogId = blogId;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BlogMessage> getBlogMessages() {
        return blogMessages;
    }

    public void setBlogMessages(List<BlogMessage> blogMessages) {
        this.blogMessages = blogMessages;
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
        return new StringJoiner(", ", BlogInfo.class.getSimpleName() + "[", "]")
                .add("blogId=" + blogId)
                .add("name='" + name + "'")
                .add("book='" + book + "'")
                .add("blog=" + blog)
                .add("createTime=" + createTime)
                .add("blogInfoDb=" + blogInfoDb)
                .add("user=" + user)
                .add("blogMessages=" + blogMessages)
                .toString();
    }
}
