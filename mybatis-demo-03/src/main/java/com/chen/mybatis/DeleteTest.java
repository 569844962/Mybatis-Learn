package com.chen.mybatis;

import com.chen.mybatis.demo3.mapper.DeleteBlogMapper;
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
 * learn 11
 * 〈mybatis delete〉
 *
 */
public class DeleteTest {

    /**
     * mybatis delete
     */
    @Test
    public void deleteMybatisTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(DeleteBlogMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            DeleteBlogMapper deleteBlogMapper = sqlSession.getMapper(DeleteBlogMapper.class);
            int i = deleteBlogMapper.deleteBlog(50);
            System.out.println("删除成功：" + i);
            sqlSession.commit();

        }
    }

    /**
     * java PreparedStatement
     * 数据库 删除数据
     */
    @Test
    public void deletePreparedStatementTest() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis-test?serverTimezone=UTC", "root", "root");
            //3.获得预处理对象
            String sql = "DELETE FROM T_BLOG  where ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 49);
            //4.执行SQL语句
            resultSet = preparedStatement.executeUpdate();
            System.out.println("删除成功：" + resultSet);
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
