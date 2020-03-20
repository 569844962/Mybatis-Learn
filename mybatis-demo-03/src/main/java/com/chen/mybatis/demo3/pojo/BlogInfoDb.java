package com.chen.mybatis.demo3.pojo;

import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.2.29
 * @company
 */
public class BlogInfoDb {

    private Integer blogInfoId;

    private Integer blogId;

    private String detail;

    public Integer getBlogInfoId() {
        return blogInfoId;
    }

    public void setBlogInfoId(Integer blogInfoId) {
        this.blogInfoId = blogInfoId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BlogInfoDb.class.getSimpleName() + "[", "]")
                .add("blogInfoId=" + blogInfoId)
                .add("blogId=" + blogId)
                .add("detail='" + detail + "'")
                .toString();
    }
}
