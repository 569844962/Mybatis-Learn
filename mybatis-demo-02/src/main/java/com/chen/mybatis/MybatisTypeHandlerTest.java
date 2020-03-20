package com.chen.mybatis;

import com.chen.mybatis.demo2.mapper.BlogTypeHandlerCustomMapper;
import com.chen.mybatis.demo2.mapper.BlogTypeHandlerMapper;
import com.chen.mybatis.demo2.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *  learn 04
 * 〈 Mybatis 类型转换 typeHandler <typeHandler></typeHandler>〉
 *  将jdbc类型转换为java类型
 *  1.自定义handler extends BaseTypeHandler
 *      @MappedJdbcTypes jdbc类型
 *      @MappedTypes    java类型
 *  2.mybatis configuration加入（使用方法一）
 *      2.1 指定单个typeHandler
 *          <typeHandlers>
 *              <typeHandler handler=""></typeHandler>
 *          </typeHandlers>
 *      2.2 指定包下的所有typeHandler
 *          <package name=""/>
 *  3.mapper XMl加入（使用方法二）
 *      3.1 插入数据库：#{books,typeHandler=com.chen.mybatis.demo2.typehandler.GenericTypeHandler,javaType=java.util.HashSet}
 *      3.2 读取：
 *       <resultMap id="result"  type="com.chen.mybatis.demo2.pojo.Blog">
 *         <result property="books" column="BOOK" typeHandler="com.chen.mybatis.demo2.typehandler.GenericTypeHandler" javaType="java.util.HashSet" />
 *       </resultMap>
 * @author chenmingjun
 * @create 2019.12.29
 * @company
 */
public class MybatisTypeHandlerTest {


    /**
     * 类型转换器：typeHandlers,在mybatis-config中指定 typehandler（方法一）
     * 将时间内省转为long，相反也可以
     */
    @Test
    public void typeHandlerTest() throws IOException {
        String configResource = "mybatis-type-handler-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogTypeHandlerMapper mapper = sqlSession.getMapper(BlogTypeHandlerMapper.class);

            Blog blog = new Blog();
            blog.setName("Mybatis-TypeHandler");
            blog.setCreateTime(new Date());
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
     * 泛型 TypeHandler，在mapper指定（方法二）
     * 实现list直接存储到数据库varchar字段
     * 1.插入数据库：#{books,typeHandler=com.chen.mybatis.demo2.typehandler.GenericTypeHandler,javaType=java.util.HashSet}
     * 2.读取：
     *   <resultMap id="result"  type="com.chen.mybatis.demo2.pojo.Blog">
     *        <result property="books" column="BOOK" typeHandler="com.chen.mybatis.demo2.typehandler.GenericTypeHandler" javaType="java.util.HashSet" />
     *   </resultMap>
     */
    @Test
    public void typeHandlerCustomTest() throws IOException {
        String configResource = "mybatis-type-handler-custom-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogTypeHandlerCustomMapper mapper = sqlSession.getMapper(BlogTypeHandlerCustomMapper.class);

            HashSet<String> objects = new HashSet<>();
            objects.add("《老人与海》");
            objects.add("《大学1》");

            Blog blog = new Blog();
            blog.setName("Mybatis-TypeHandler-CUSTOM");
            blog.setCreateTime(new Date());
            blog.setBooks(objects);

            Long i = mapper.insertBlog(blog);
            sqlSession.commit();
            System.out.println(blog.getId());
            if (i > 0) {
                Blog blogRest = mapper.selectOne(blog.getId());
                System.out.println(blogRest);
            }
        }
    }

}
