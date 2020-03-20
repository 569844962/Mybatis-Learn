package com.chen.mybatis;

import com.chen.mybatis.demo2.mapper.BlogTypeliaseMapper;
import com.chen.mybatis.demo2.pojo.BlogTypealiase;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 *  learn 03
 * 〈 Mybatis 类型别名 typeAliases <typeAliases></typeAliases>〉
 *
 * @author chenmingjun
 * @create 2019.12.29
 * @company
 */
public class MybatisTypeAliasesTest {

    /**
     * typeAliases 配置别名
     *     <typeAliases>
     *         <typeAlias type="com.chen.mybatis.demo2.pojo.BlogTypealiase" alias="blog"/>
     *     </typeAliases>
     */
    @Test
    public void aliasesTest() throws IOException {
        String configResource = "mybatis-typealiase-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogTypeliaseMapper mapper = sqlSession.getMapper(BlogTypeliaseMapper.class);
            BlogTypealiase blog = mapper.selectOne(1L);
            System.out.println(blog);
        }
    }

    /**
     * typeAliases 批量配置别名
     * 1. 指定包名
     *     <typeAliases>
     *         <package name="com.chen.mybatis.demo2.pojo"/>
     *     </typeAliases>
     * 2.包名下的所有类自动加别名，别名为类型，首字母小写
     */
    @Test
    public void aliasesPackageTest() throws IOException {
        String configResource = "mybatis-typealiase-package-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogTypeliaseMapper mapper = sqlSession.getMapper(BlogTypeliaseMapper.class);
            BlogTypealiase blogTypealiase = mapper.selectOne(1L);
            System.out.println(blogTypealiase);
        }
    }


    @Test
    public void aliasesJavaTest() throws IOException {
        String configResource = "mybatis-typealiase-java-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            Configuration configuration = sqlSession.getConfiguration();
            TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
            typeAliasRegistry.registerAlias("blogTypealiase",com.chen.mybatis.demo2.pojo.BlogTypealiase.class);

            configuration.addMapper(BlogTypeliaseMapper.class);

            BlogTypeliaseMapper mapper = sqlSession.getMapper(BlogTypeliaseMapper.class);
            BlogTypealiase blogTypealiase = mapper.selectOne(1L);
            System.out.println(blogTypealiase);
        }
    }


        @Test
        public void aliasesJava2Test() throws IOException {
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

        configuration.getTypeAliasRegistry().registerAlias("blogTypealiase",com.chen.mybatis.demo2.pojo.BlogTypealiase.class);

        //加入Mapper
        configuration.addMapper(BlogTypeliaseMapper.class);

                SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogTypeliaseMapper mapper = sqlSession.getMapper(BlogTypeliaseMapper.class);
            BlogTypealiase blogTypealiase = mapper.selectOne(1L);
            System.out.println(blogTypealiase);
        }

    }


}
