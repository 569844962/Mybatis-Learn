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
import java.util.Properties;

/**
 *  learn 02
 * 〈Myabtis配置〉
 *
 * @author chenmingjun
 * @create 2019.12.27
 * @company
 */
public class MybatisPropertiesTest {

    /**
     * learn 1.属性（properties）
     * 从 properties 引入配置属性
     */
    @Test
    public void properties() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = build.openSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectOne(1L);
            System.out.println(blog);
        }
    }

    @Test
    public void propertiesJava() throws IOException {
        String resource = "mybatis-config2.xml";
        InputStream resourceConfig = Resources.getResourceAsStream(resource);
        Properties properties = new Properties();
        //1，2，3效果一致
        //1.加载config.properties配置
        String dbProperties = "config.properties";
        InputStream resourceAsStream = Resources.getResourceAsStream(dbProperties);
        properties.load(resourceAsStream);
        //2.set 配置值
//        properties.setProperty("driver", "com.mysql.cj.jdbc.Driver");
//        properties.setProperty("url", "jdbc:mysql://localhost:3306/mybatis-test?serverTimezone=UTC");
//        properties.setProperty("username", "root");
//        properties.setProperty("password", "root");
        //3.set properties xml
//        String propertiesXMLResource = "properties-config.xml";
//        InputStream propertiesXML = Resources.getResourceAsStream(propertiesXMLResource);
//        properties.loadFromXML(propertiesXML);

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceConfig, properties);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectOne(1L);
            System.out.println(blog);
        }
    }
}
