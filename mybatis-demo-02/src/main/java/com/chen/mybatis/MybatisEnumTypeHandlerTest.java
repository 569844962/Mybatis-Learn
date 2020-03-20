package com.chen.mybatis;

import com.chen.mybatis.demo2.emuns.BlogGenericeTypeEnum;
import com.chen.mybatis.demo2.mapper.BlogCommonEnumTypeHandlerMapper;
import com.chen.mybatis.demo2.mapper.BlogEnumTypeHandlerMapper;
import com.chen.mybatis.demo2.mapper.BlogGenericEnumTypeHandlerMapper;
import com.chen.mybatis.demo2.mapper.BlogOrdinalEnumTypeHandlerMapper;
import com.chen.mybatis.demo2.pojo.Blog;
import com.chen.mybatis.demo2.emuns.BlogTypeEnum;
import com.chen.mybatis.demo2.pojo.BlogGeneric;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 *  learn 05
 * 〈 Mybatis 枚举类型转换〉
 * 1.mybatis自带两个枚举enumtypehandler
 *  EnumOrdinalTypeHandler  插入的是枚举的序号
 *  EnumTypeHandler 插入的是枚举名
 *  两者都有局限性
 *  2.某个枚举类的EnumTypeHandler
 *  CommonEnumTypeHandler继承BaseHandlerType<BlogTypeEnum>
 *  可用将枚举的任意属性存入库中，但仅能用于某个枚举类，这就是他的局限性
 *
 *
 * @author chenmingjun
 * @create 2019.12.29
 * @company
 */
public class MybatisEnumTypeHandlerTest {


    /**
     * 01
     * EnumTypeHandler
     * 类型转换器：typeHandlers,在mybatis.xml 中指定
     * 参数：#{blogType,typeHandler=org.apache.ibatis.type.EnumTypeHandler,jdbcType=VARCHAR}
     * resultMap：
     * <resultMap>
     *  <result property="blogType" column="BLOG_TYPE" typeHandler="org.apache.ibatis.type.EnumTypeHandler" javaType="com.chen.mybatis.demo2.emuns.BlogTypeEnum" jdbcType="VARCHAR"/>
     * </resultMap>
     *
     * 说明：org.apache.ibatis.type.EnumTypeHandler
     * 插入数据库的时候会插入枚举的定义值：如，BlogTypeEnum.SPORTS 插入SPORTS
     */
    @Test
    public void typeHandlerEnumTest() throws IOException {
        String configResource = "mybatis-type-handler-enum-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogEnumTypeHandlerMapper mapper = sqlSession.getMapper(BlogEnumTypeHandlerMapper.class);

            Blog blog = new Blog();
            blog.setName("Mybatis-Enum-TypeHandler");
            blog.setCreateTime(new Date());
            blog.setBlogType(BlogTypeEnum.SPORTS);
            Long i = mapper.insertBlog(blog);
            sqlSession.commit();
            System.out.println(blog.getId());
            if (i > 0) {
                Blog blogRest = mapper.selectOne(blog.getId());
                System.out.println(blogRest);
            }
        }
    }


    /**
     * 02
     * EnumOrdinalTypeHandler
     * 类型转换器：typeHandlers,在mybatis.xml 中指定
     * 参数：#{blogType,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler,jdbcType=VARCHAR}
     * resultMap：
     * <resultMap>
     *  <result property="blogType" column="BLOG_TYPE" typeHandler="org.apache.ibatis.type.EnumOrdinalTypeHandler" javaType="com.chen.mybatis.demo2.emuns.BlogTypeEnum" jdbcType="VARCHAR"/>
     * </resultMap>
     *
     * 说明：org.apache.ibatis.type.EnumTypeHandler
     * 插入数据库的时候会插入枚举的序列值（Ordinal）：如，BlogTypeEnum.SPORTS 插入Ordinal值 0/1
     */
    @Test
    public void typeHandlerOrdinalEnumTest() throws IOException {
        String configResource = "mybatis-type-handler-enum-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        factory.getConfiguration().addMapper(BlogOrdinalEnumTypeHandlerMapper.class);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogOrdinalEnumTypeHandlerMapper mapper = sqlSession.getMapper(BlogOrdinalEnumTypeHandlerMapper.class);
            Blog blog = new Blog();
            blog.setName("Mybatis-Ordinal-Enum-TypeHandler");
            blog.setCreateTime(new Date());
            blog.setBlogType(BlogTypeEnum.NEW);
            Long i = mapper.insertBlog(blog);
            sqlSession.commit();
            System.out.println(blog.getId());
            if (i > 0) {
                Blog blogRest = mapper.selectOne(blog.getId());
                System.out.println(blogRest);
            }
        }
    }

    /**
     * 03
     * CommonEnumTypeHandler
     * 通过继承BaseTypeHandler来实现指定枚举类与数据库类型的转换
     */
    @Test
    public void typeHandlerCommonEnumTest() throws IOException {
        String configResource = "mybatis-type-handler-enum-common-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogCommonEnumTypeHandlerMapper mapper = sqlSession.getMapper(BlogCommonEnumTypeHandlerMapper.class);
            Blog blog = new Blog();
            blog.setName("Mybatis-common-Enum-TypeHandler");
            blog.setCreateTime(new Date());
            blog.setBlogType(BlogTypeEnum.NEW);
            Long i = mapper.insertBlog(blog);
            sqlSession.commit();
            System.out.println(blog.getId());
            if (i > 0) {
                Blog blogRest = mapper.selectOne(blog.getId());
                System.out.println(blogRest);
            }
        }

    }

    /**
     * 04
     * GenericEnumTypeHandler
     * 通过继承BaseTypeHandler来实现指定枚举类与数据库类型的转换
     */
    @Test
    public void typeHandlerGenericEnumTest() throws IOException {
        String configResource = "mybatis-type-handler-enum-generic-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogGenericEnumTypeHandlerMapper mapper = sqlSession.getMapper(BlogGenericEnumTypeHandlerMapper.class);
            BlogGeneric blog = new BlogGeneric();
            blog.setName("Mybatis-generic-Enum-TypeHandler");
            blog.setCreateTime(new Date());
            blog.setBlogType(BlogGenericeTypeEnum.SPORTS);
            Long i = mapper.insertBlog(blog);
            sqlSession.commit();
            System.out.println(blog.getId());
            if (i > 0) {
                BlogGeneric blogRest = mapper.selectOne(blog.getId());
                System.out.println(blogRest);
            }
        }

    }

}
