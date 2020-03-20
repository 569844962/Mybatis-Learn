package com.chen.mybatis.demo2.mapper;

/**
 * 〈ORACLE 学生Mapper〉
 *
 * @author chenmingjun
 * @create 2020.2.5
 * @company
 */
public interface StudentMapper {

    /**
     * 查询姓名
     * @param id
     * @return
     */
    String selectNameById(String id);
}
