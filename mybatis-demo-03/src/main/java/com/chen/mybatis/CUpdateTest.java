package com.chen.mybatis;

import com.chen.mybatis.demo3.mapper.CUpdateBlogMapper;
import com.chen.mybatis.demo3.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * learn 10
 * 〈mybatis update〉
 *
 */
public class CUpdateTest {

    /**
     * mybatis update
     */
    @Test
    public void updateMybatisTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(CUpdateBlogMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Blog blog = new Blog();
            blog.setName("陈二帆");
            blog.setBook("《小老虎》");

            CUpdateBlogMapper updateBlogMapper = sqlSession.getMapper(CUpdateBlogMapper.class);
            int i = updateBlogMapper.updateBlog(blog);
            System.out.println("更新成功：" + i + " data:" + blog);
            sqlSession.commit();

        }
    }

    /**
     * java PreparedStatement
     * 数据库 更新数据
     */
    @Test
    public void updatePreparedStatementTest() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis-test?serverTimezone=UTC", "root", "root");
            //3.获得预处理对象
            String sql = "update T_BLOG SET BOOK = ? where NAME = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "《小老虎2》");
            preparedStatement.setString(2,"陈二帆");
            //4.执行SQL语句
            resultSet = preparedStatement.executeUpdate();
            System.out.println("更新成功：" + resultSet);
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
        }
    }
}
