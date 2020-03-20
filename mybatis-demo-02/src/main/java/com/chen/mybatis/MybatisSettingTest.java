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

/**
 *  learn 01
 * 〈Mybatis set <settings></settings>〉
 *
 * @author chenmingjun
 * @create 2019.12.29
 * @company
 */
public class MybatisSettingTest {

    @Test
    public void setTest() throws IOException {
        String configResource = "mybatis-setting-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configResource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = factory.openSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectOne(1L);
            System.out.println(blog);
        }
    }
}
