package com.chen.mybatis.demo3.pojo;

import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.2.24
 * @company
 */
public class BlogMessage {

    private Integer messageId;

    private Integer blogId;

    private String message;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BlogMessage.class.getSimpleName() + "[", "]")
                .add("messageId=" + messageId)
                .add("blogId=" + blogId)
                .add("message='" + message + "'")
                .toString();
    }
}
