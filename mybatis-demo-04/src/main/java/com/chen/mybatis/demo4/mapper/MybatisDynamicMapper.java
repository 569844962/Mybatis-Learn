package com.chen.mybatis.demo4.mapper;

import com.chen.mybatis.demo4.pojo.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.3.2
 * @company
 */
public interface MybatisDynamicMapper {
    List<Blog> selectListByName(@Param("name") String name);

    List<Blog> selectListWithChoose(@Param("name1") String name1, @Param("name2") String name2, @Param("name3") String name3);

    List<Blog> selectListWithWhere(@Param("name") String name);

    int updateWithSet(@Param("id") String id, @Param("name") String name, @Param("book") String book);

    List<Blog> selectListWithTrimToWhere(@Param("id") String id,@Param("name") String name);

    int updateWithTrimToSet(@Param("id") String id, @Param("name") String name, @Param("book") String book);

    List<Blog> selectWithForeach(@Param("ids") List<String> idList
    );

    @Select({"<script> ",
            "select * from t_blog ",
            "<where> ",
                "<if test = 'name != null'>NAME = #{name}</if> ",
            "</where> ",
            "</script>"}
    )
    List<Blog> selectWithScript(@Param("name") String name);
}
