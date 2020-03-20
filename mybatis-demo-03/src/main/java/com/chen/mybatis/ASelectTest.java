package com.chen.mybatis;

import com.chen.mybatis.demo3.mapper.ASelectBlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;

/**
 * learn 08
 * 〈mybatis select〉
 *
 * @author chenmingjun
 * @create 2020.2.6
 * @company
 */
public class ASelectTest {

    /**
     * mybatis select
     * select 根据入参查询数据
     */
    @Test
    public void selectMybatisTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(ASelectBlogMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ASelectBlogMapper selectBlogMapper = sqlSession.getMapper(ASelectBlogMapper.class);
            HashMap hashMap = selectBlogMapper.selectOne(1L);
            System.out.println(hashMap);
        }
    }

    /**
     * java PreparedStatement
     * 查询数据库
     * @throws Exception
     */
    @Test
    public void selectPreparedStatementTest() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis-test?serverTimezone=UTC", "root", "root");
            //3.获得预处理对象
            String sql = "SELECT * FROM T_BLOG WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "1");
            //4.执行SQL语句
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String string = resultSet.getString(1);
                String str2 = resultSet.getString(2);
                System.out.println(string + ":" + str2);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }

        }
    }
}
