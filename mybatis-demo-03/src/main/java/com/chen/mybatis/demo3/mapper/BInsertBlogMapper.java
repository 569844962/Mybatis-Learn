package com.chen.mybatis.demo3.mapper;


import com.chen.mybatis.demo3.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BInsertBlogMapper {

    int insertBlog(Blog blog);

    int insertBlogList(@Param("blogs") List<Blog> blogs);

    int insertBlogWithSelectKey(Blog blog);
}
