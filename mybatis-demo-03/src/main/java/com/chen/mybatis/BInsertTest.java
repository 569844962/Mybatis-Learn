package com.chen.mybatis;

import com.chen.mybatis.demo3.mapper.BInsertBlogMapper;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * learn 09
 * 1.〈mybatis insert〉
 * 2.useGeneratedKeys 返回主建
 * 3.selectKey 生成ID，并赋值
 */
public class BInsertTest {

    /**
     * mybatis insert
     */
    @Test
    public void insertMybatisTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(BInsertBlogMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Blog blog = new Blog();
            blog.setName("陈二帆");
            blog.setBook("《老人与海》;《我和僵尸有个约会》");
            blog.setCreateTime(new Date());

            BInsertBlogMapper insertBlogMapper = sqlSession.getMapper(BInsertBlogMapper.class);
            int i = insertBlogMapper.insertBlog(blog);
            System.out.println(blog);
            sqlSession.commit();

        }
    }

    /**
     * java PreparedStatement
     * 数据库 插入数据
     * @throws Exception
     */
    @Test
    public void insertPreparedStatementTest() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis-test?serverTimezone=UTC", "root", "root");
            //3.获得预处理对象
            String sql = "INSERT INTO T_BLOG(NAME,CREATE_TIME,BOOK) value (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "1");
            preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setString(3,"《圣经》;《我的大学》");
            //4.执行SQL语句
            resultSet = preparedStatement.executeUpdate();
            System.out.println("插入成功：" + resultSet);
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

    /**
     * mybatis insert batch
     * 批量插入
     */
    @Test
    public void insertBatchMybatisTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(BInsertBlogMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            List<Blog> blogList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Blog blog = new Blog();
                blog.setName("陈二帆"+i);
                blog.setBook("《老人与海》;《我和僵尸有个约会》," + i);
                blog.setCreateTime(new Date());
                blogList.add(blog);
            }

            BInsertBlogMapper insertBlogMapper = sqlSession.getMapper(BInsertBlogMapper.class);
            int i = insertBlogMapper.insertBlogList(blogList);
            System.out.println(blogList);
            sqlSession.commit();

        }
    }

    /**
     * selectKey 生成主建值
     * keyProperty	 :selectKey 语句结果应该被设置的目标属性。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表。
     * keyColumn	 :匹配属性的返回结果集中的列名称。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表。
     * resultType	 :结果的类型。MyBatis 通常可以推断出来，但是为了更加精确，写上也不会有什么问题。MyBatis 允许将任何简单类型用作主键的类型，包括字符串。如果希望作用于多个生成的列，则可以使用一个包含期望属性的 Object 或一个 Map。
     * order	     :这可以被设置为 BEFORE 或 AFTER。如果设置为 BEFORE，那么它会首先生成主键，设置 keyProperty 然后执行插入语句。如果设置为 AFTER，那么先执行插入语句，然后是 selectKey 中的语句 - 这和 Oracle 数据库的行为相似，在插入语句内部可能有嵌入索引调用。
     * statementType :与前面相同，MyBatis 支持 STATEMENT，PREPARED 和 CALLABLE 语句的映射类型，分别代表 PreparedStatement 和 CallableStatement 类型。
     */
    @Test
    public void selectKeyTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(BInsertBlogMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BInsertBlogMapper insertBlogMapper = sqlSession.getMapper(BInsertBlogMapper.class);
            Blog blog = new Blog();
            blog.setName("selectKey");
            blog.setBook("《天才在左，疯子在右》");
            int i = insertBlogMapper.insertBlogWithSelectKey(blog);
            System.out.println(blog);
            sqlSession.commit();
        }
    }
}
