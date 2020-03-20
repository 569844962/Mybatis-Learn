package com.chen.mybatis;

import com.chen.mybatis.demo3.mapper.ESqlMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * learn 12
 * 〈mybatis sql〉
 * 这个元素可以被用来定义可重用的 SQL 代码段，这些 SQL 代码可以被包含在其他语句中。
 */
public class ESqlTest {

    /**
     * SQL 占位符
     * 在不同的包含语句中可以设置不同的值到参数占位符上
     */
    @Test
    public void sqlPlaceholderMybatisTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(ESqlMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ESqlMapper sqlMapper = sqlSession.getMapper(ESqlMapper.class);
            HashMap list = sqlMapper.sqlPlaceholderSelect();
            System.out.println("查询成功：" + list);
            sqlSession.commit();
        }
    }


    /**
     * 更加灵活的sql配置使用
     * @throws IOException
     */
    @Test
    public void sqlInnerPropertySelectMybatisTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(ESqlMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ESqlMapper sqlMapper = sqlSession.getMapper(ESqlMapper.class);
            HashMap list = sqlMapper.sqlInnerPropertySelect();
            System.out.println("查询成功：" + list);
            sqlSession.commit();
        }
    }

}
