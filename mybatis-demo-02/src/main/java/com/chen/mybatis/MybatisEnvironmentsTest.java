package com.chen.mybatis;

import com.chen.mybatis.demo2.mapper.BlogMapper;
import com.chen.mybatis.demo2.mapper.StudentMapper;
import com.chen.mybatis.demo2.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 *  learn 07
 * 〈environments 配置〉
 *
 */
public class MybatisEnvironmentsTest {

    /**
     * learn 07-01
     * 创建两个sqlSessionFactory分别连接mysql和oracle数据库
     * @throws IOException
     */
    @Test
    public void sqlSessionFactory() throws IOException {
        String resource = "mybatis-environments-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory mysqlDevelopment = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource), "mysql_development");
        SqlSessionFactory oracleDevelopment = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource), "oracle_development");
        //1.mysql sqlSessionFactory 数据库查询
        try (SqlSession sqlSession = mysqlDevelopment.openSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectOne(1L);
            System.out.println("mysql sqlSessionFactory:" + blog);
        }

        //2.oracle sqlSessionFactory 数据库查询
        try (SqlSession sqlSession = oracleDevelopment.openSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            String name = mapper.selectNameById("1");
            System.out.println("oracle sqlSessionFactory:" + name);
        }


    }
}
