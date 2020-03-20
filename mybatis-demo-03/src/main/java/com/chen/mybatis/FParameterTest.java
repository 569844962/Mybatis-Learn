package com.chen.mybatis;

import com.chen.mybatis.demo3.mapper.FParameterMapper;
import com.chen.mybatis.demo3.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * learn 12
 * 〈Mybatis 参数〉
 *
 * @author chenmingjun
 * @create 2020.2.15
 * @company
 */
public class FParameterTest {

    private SqlSessionFactory initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    /**
     * 最简单的mybatis参数使用
     */
    @Test
    public void simpleParameterMybatisTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        sqlSessionFactory.getConfiguration().addMapper(FParameterMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            FParameterMapper parameterMapper = sqlSession.getMapper(FParameterMapper.class);
            Blog blog = parameterMapper.selectBlogById(1L);
            sqlSession.commit();
            System.out.println("查询成功：" + blog);
        }
    }

    /**
     * 插入数据库
     * 传入对象到语句中，对应的参数指会被自动查找，然后将他们的值传入预处理语句中
     * @throws IOException
     */
    @Test
    public void insertParameterMybatisTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        sqlSessionFactory.getConfiguration().addMapper(FParameterMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            FParameterMapper parameterMapper = sqlSession.getMapper(FParameterMapper.class);
            Blog blog = new Blog();
            blog.setName("晃一晃");
            blog.setBook("《java的1000000个疑问》");
            blog.setCreateTime(new Date());
            int i = parameterMapper.insertBlog(blog);
            sqlSession.commit();
            System.out.println(i);
        }
    }

    /**
     * 插入数据库 hashmap
     * 通过HashMap传入参数，mybatis不知道每个参数的具体数据类型，所以需要指定
     * javaType和jdbcType
     *
     * @throws IOException
     */
    @Test
    public void insertParameterHashMapMybatisTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        sqlSessionFactory.getConfiguration().addMapper(FParameterMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            FParameterMapper parameterMapper = sqlSession.getMapper(FParameterMapper.class);
            HashMap<String, Object> blogHashMap = new HashMap<>();
            blogHashMap.put("name", "摇一摇");
            blogHashMap.put("book", "《千里之外》");
            blogHashMap.put("createTime", new Date());
            blogHashMap.put("price", new Double(22.2324));
            int i = parameterMapper.insertBlogWithHashMap(blogHashMap);
            sqlSession.commit();
            System.out.println(i);
        }
    }

    /**
     * mybatis 字符串替换
     * ${} 为替换符，会被直接替换掉
     * #{} 为占位符，会被使用？预处理
     */
    @Test
    public void stringParameterMybatisTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        sqlSessionFactory.getConfiguration().addMapper(FParameterMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            FParameterMapper mapper = sqlSession.getMapper(FParameterMapper.class);

            //1.通过${}替换符，实现自定义字段排序
            List<Blog> blogs = mapper.selectBlogListOrderBy("CREATE_TIME");
            System.out.println(blogs);

            //2.通过${}替换符和#{}占位符的结合，实现自定义查询
            List<Blog> blogs1 = mapper.selectBlogListByColumn("NAME", "摇一摇");
            System.out.println(blogs1);

            //3.使用${}替换符传入sql语句
            String sql = "SELECT * FROM t_blog where NAME = '摇一摇' LIMIT 1";
            Blog blog = mapper.selectBlogWithSql(sql);
            System.out.println("使用${}替换符传入sql语句:" + blog);

            //4.使用${},看看sql注入
            Blog blog2 = mapper.selectBlogByIDInto("0");
            Blog blog3 = mapper.selectBlogByIDInto("0 || 1=1 limit 1");
            System.out.println("A.使用${},看看sql注入:" + blog2);
            System.out.println("B.使用${},看看sql注入:" + blog3);

            //5.使用#{}占位符
            Blog blog4 = mapper.selectBlogByIdToString("0 || 1=1 limit 1");
            System.out.println("使用#{}占位符:" + blog4);
        }
    }

}
