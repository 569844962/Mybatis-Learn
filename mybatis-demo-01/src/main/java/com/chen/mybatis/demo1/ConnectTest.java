package com.chen.mybatis.demo1;

import com.chen.mybatis.demo1.mapper.BlogMapper;
import com.chen.mybatis.demo1.pojo.Blog;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * 〈sqlSessionFactory两种构建方式〉
 *
 * @author chenmingjun
 * @create 2019.12.19
 * @company
 */
public class ConnectTest {

    /**
     * learn
     * XML 方式构建sqlSessionFactory
     */
    @Test
    public void connectFromXml() throws IOException {
        String resourcePath = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resourcePath);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            Blog o = sqlSession.selectOne("com.chen.mybatis.demo1.mapper.BlogMapper.selectOne", 1);
            System.out.println(o);
        }
    }

    /**
     * learn
     * 配置信息写入properties文件
     * XML 方式构建sqlSessionFactory
     */
    @Test
    public void connectFromXmlWithProperties() throws IOException {
        String resourcePath = "mybatis-config2.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resourcePath);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            Blog o = sqlSession.selectOne("com.chen.mybatis.demo1.mapper.BlogMapper.selectOne", 1);
            System.out.println(o);
        }
    }

    /**
     * learn
     * java 方式构建sqlSessionFactory
     */
    @Test
    public void connectFromJava() {
        //创建连接池
        DataSource dataSource = new PooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/mybatis-test?serverTimezone=UTC", "root", "root");
        //事务
        TransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        //创建环境
        Environment development = new Environment("development", jdbcTransactionFactory, dataSource);
        //创建配置
        Configuration configuration = new Configuration(development);
        //开启驼峰规则
        configuration.setMapUnderscoreToCamelCase(true);
        //加入Mapper
        configuration.addMapper(BlogMapper.class);

        //
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectOne(1L);
            System.out.println(blog);
        }
    }
}
