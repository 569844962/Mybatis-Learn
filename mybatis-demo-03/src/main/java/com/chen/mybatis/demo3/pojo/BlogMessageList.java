package com.chen.mybatis.demo3.pojo;

import java.util.List;
import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.2.25
 * @company
 */
public class BlogMessageList {

    private List<BlogMessage> blogMessageList;

    public List<BlogMessage> getBlogMessageList() {
        return blogMessageList;
    }

    public void setBlogMessageList(List<BlogMessage> blogMessageList) {
        this.blogMessageList = blogMessageList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BlogMessageList.class.getSimpleName() + "[", "]")
                .add("blogMessageList=" + blogMessageList)
                .toString();
    }
}
