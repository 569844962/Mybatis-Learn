package com.chen.mybatis.demo2.mapper;


import com.chen.mybatis.demo2.pojo.Blog;

/**
 * 〈泛型类型处理器〉
 *
 * @author chenmingjun
 * @create 2019.12.20
 * @company
 */
public interface BlogTypeHandlerCustomMapper {

    Long insertBlog(Blog blog);

    Blog selectOne(Long id);
}
