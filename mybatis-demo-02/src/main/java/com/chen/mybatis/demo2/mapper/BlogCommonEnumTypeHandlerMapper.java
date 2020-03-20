package com.chen.mybatis.demo2.mapper;


import com.chen.mybatis.demo2.pojo.Blog;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2019.12.20
 */
public interface BlogCommonEnumTypeHandlerMapper {

    Long insertBlog(Blog blog);

    Blog selectOne(Long id);
}
