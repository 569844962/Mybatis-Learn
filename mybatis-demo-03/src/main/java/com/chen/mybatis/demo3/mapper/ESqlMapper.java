package com.chen.mybatis.demo3.mapper;


import java.util.HashMap;

public interface ESqlMapper {

    /**
     * sql 占位符
     * @return
     */
    HashMap sqlPlaceholderSelect();

    HashMap sqlInnerPropertySelect();

}
