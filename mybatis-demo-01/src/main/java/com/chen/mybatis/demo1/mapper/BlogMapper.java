package com.chen.mybatis.demo1.mapper;

import com.chen.mybatis.demo1.pojo.Blog;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2019.12.20
 * @company
 */
public interface BlogMapper {

    Blog selectOne(Long id);
}
