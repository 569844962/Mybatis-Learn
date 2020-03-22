package com.chen.mybatis.demo4;

import com.chen.mybatis.demo4.mapper.MybatisDynamicMapper;
import com.chen.mybatis.demo4.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 〈Mybatis 动态SQL〉
 *
 * @author chenmingjun
 * @create 2020.3.2
 * @company
 */
public class MybatisTest {

    public SqlSessionFactory initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(MybatisDynamicMapper.class);
        return sqlSessionFactory;
    }


    /**
     * Mybatis if
     */
    @Test
    public void selectWithIf() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            List<Blog> blogList = mapper.selectListByName("o博客");
            System.out.println(blogList);
        }
    }

    /**
     * choose, when, otherwise
     * 条件选择
     */
    @Test
    public void selectWithChoose() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            List<Blog> blogList = mapper.selectListWithChoose(null,"o博客","te");
            System.out.println(blogList);
        }
    }

    /**
     * mybatis where 元素
     * 避免 where and 的sql问题
     */
    @Test
    public void selectWithWhere() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            List<Blog> blogList = mapper.selectListWithWhere("博客AA");
            System.out.println(blogList);
        }
    }

    /**
     * mybatis set 元素
     * 避免 update table set column1 = 2,  的问题 ，多了 ","导致sql出错
     */
    @Test
    public void selectWithSet() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            int i = mapper.updateWithSet("1","博客AA","《撒哈拉沙漠1》");
            sqlSession.commit();
            System.out.println(i);
        }
    }

    /**
     * mybatis trim where
     * 去除 where前and/or ，防止sql出错
     * trim
     * -- prefix : 给sql语句拼接的前缀
     * -- suffix : 给sql语句拼接的后缀
     * -- prefixOverrides : 去除sql语句前面的关键字或者字符，该关键字或者字符由prefixOverrides属性指定，假设该属性指定为"AND"，当sql语句的开头为"AND"，trim标签将会去除该"AND"
     * -- suffixOverrides : 去除sql语句后面的关键字或者字符，该关键字或者字符由suffixOverrides属性指定
     */
    @Test
    public void selectWithTrimToWhere() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            List<Blog> blog = mapper.selectListWithTrimToWhere(null,"o博客");
            System.out.println(blog);
        }
    }

    /**
     * mybatis trim set
     * 去除末尾“,”防止sql出错
     */
    @Test
    public void selectWithTrimToSet() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            int i = mapper.updateWithTrimToSet("1","o博客",null);
            sqlSession.commit();
            System.out.println(i);
        }
    }


    /**
     * mybatis foreach
     * 1.foreach 允许你指定一个集合，声明可以在元素体内使用的集合项（item）和索引（index）变量。
     * 它也允许你指定开头与结尾的字符串以及在迭代结果之间放置分隔符。这个元素是很智能的，因此它不会偶然地附加多余的分隔符。
     *
     * 2.你可以将任何可迭代对象（如 List、Set 等）、Map 对象或者数组对象传递给 foreach 作为集合参数。
     * 当使用可迭代对象或者数组时，index 是当前迭代的次数，item 的值是本次迭代获取的元素。
     * 当使用 Map 对象（或者 Map.Entry 对象的集合）时，index 是键，item 是值。
     *
     * <foreach></foreach>
     * -- collection 集合（入参List,Set）
     * -- item 当前对象
     * -- open 循环开始的分隔符
     * -- close 循环结束的分隔符
     * -- separator 每次遍历的间隔符
     */
    @Test
    public void selectWithForeach() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            List<Blog> blogList = mapper.selectWithForeach(Arrays.asList("1", "2", "3"));
            System.out.println(blogList);
        }
    }

    /**
     * mybatis script 注解上使用动态查询
     *
     */
    @Test
    public void selectWithScript() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            List<Blog> blogList = mapper.selectWithScript("o博客");
            System.out.println(blogList);
        }
    }

    /**
     * mybatis bind xml文件中创建一个变量
     *
     */
    @Test
    public void selectWithBind() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            List<Blog> blogList = mapper.selectWithBind("o博客");
            System.out.println(blogList);
        }
    }

    /**
     * mybatis 获取数据库类型
     *
     */
    @Test
    public void getDatabaseId() throws IOException, SQLException {
        SqlSessionFactory sqlSessionFactory = this.initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Connection connection = sqlSession.getConnection();
            String databaseProductName = connection.getMetaData().getDatabaseProductName();
            String databaseProductVersion = connection.getMetaData().getDatabaseProductVersion();
            System.out.println("数据库版本=====" + databaseProductName + ":" + databaseProductVersion);
            MybatisDynamicMapper mapper = sqlSession.getMapper(MybatisDynamicMapper.class);
            String databaseId = mapper.getDatabaseId();
            System.out.println(databaseId);
        }
    }



}
