package com.chen.mybatis.demo3.mapper;

import com.chen.mybatis.demo3.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 〈mybatis parameter mapper〉
 *
 * @author chenmingjun
 * @create 2020.2.15
 * @company
 */
public interface FParameterMapper {

    /**
     * 根据ID查询数据
     * @param id id
     * @return
     */
    Blog selectBlogById(Long id);

    /**
     * 插入Blog
     * Blog 类型的参数对象传递到了语句中，name 和 book和createTime 属性将会被查找，然后将它们的值传入预处理语句的参数中
     * @param blog 入参传入到mybatis
     * @return
     */
    int insertBlog(Blog blog);

    /**
     * 插入Blog表，入参为hashMap，此时你需要指定数据类型
     * @param hashMap 入参
     * @return
     */
    int insertBlogWithHashMap(HashMap hashMap);

    /**
     * 使用${}替换符，传入排序字段
     * @param orderColumn 排序字段你
     * @return
     */
    List<Blog> selectBlogListOrderBy(@Param("orderColumn") String orderColumn);

    /**
     * 使用${}和#{}，自定义查找某列的值
     * @param column 列名称
     * @param value 值
     * @return
     */
    List<Blog> selectBlogListByColumn(@Param("column") String column,@Param("value") String value);

    /**
     * 使用#{}替换符，传入sql进行执行
     * @param sql
     * @return
     */
    Blog selectBlogWithSql(@Param("sql") String sql);

    /**
     * 测试sql注入
     * @param id
     * @return
     */
    Blog selectBlogByIDInto(@Param("id") String id);

    /**
     * #{}占位符
     * @param id
     * @return
     */
    Blog selectBlogByIdToString(String id);

}
