package com.chen.mybatis.demo2.mapper;


import com.chen.mybatis.demo2.pojo.Blog;

public interface BlogOrdinalEnumTypeHandlerMapper {

    Long insertBlog(Blog blog);

    Blog selectOne(Long id);
}
