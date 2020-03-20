package com.chen.mybatis.demo2.mapper;


import com.chen.mybatis.demo2.pojo.BlogGeneric;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2019.12.20
 */
public interface BlogGenericEnumTypeHandlerMapper {

    Long insertBlog(BlogGeneric blog);

    BlogGeneric selectOne(Long id);
}
