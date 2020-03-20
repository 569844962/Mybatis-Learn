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
 *  learn 06
 * 〈插件（plugins）〉
 */
public class MybatisPluginsTest {

    /**
     * learn 06
     * <plugins>
     * MyBatis 允许使用插件来拦截的方法调用包括：
     * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
     * ParameterHandler (getParameterObject, setParameters)
     * ResultSetHandler (handleResultSets, handleOutputParameters)
     * StatementHandler (prepare, parameterize, batch, update, query)
     */
    @Test
    public void plugins() throws IOException {
        String resource = "mybatis-plugins-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlSession = build.openSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectOne(1L);
            System.out.println(blog);
        }
    }
}
