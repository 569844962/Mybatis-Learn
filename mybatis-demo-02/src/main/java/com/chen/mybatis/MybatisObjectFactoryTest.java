package com.chen.mybatis;

import com.chen.mybatis.demo2.mapper.BlogMapper;
import com.chen.mybatis.demo2.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 *  learn 06
 * 〈ObjectFactory〉
 *
 * @author chenmingjun
 * @create 2019.12.27
 * @company
 */
public class MybatisObjectFactoryTest {

    /**
     * learn 06
     * （objectFactory）
     * MyBatis 每次创建结果对象的新实例时，
     * 它都会使用一个对象工厂（ObjectFactory）实例来完成。
     * 默认的对象工厂需要做的仅仅是实例化目标类，
     * 要么通过默认构造方法，要么在参数映射存在的时候通过参数构造方法来实例化。
     * 如果想覆盖对象工厂的默认行为，则可以通过创建自己的对象工厂来实现
     */
    @Test
    public void objectFactory() throws IOException {
        String resource = "mybatis-objectfactory-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = build.openSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectOne(1L);
            System.out.println(blog);
        }
    }
}
