package com.chen.mybatis;

import com.chen.mybatis.demo3.mapper.GResultMapMapper;
import com.chen.mybatis.demo3.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * learn 13
 * 〈mybatis ResultMap〉
 *
 * @author chenmingjun
 * @create 2020.2.18
 * @company
 */
public class GResultMapTest {

    public static SqlSessionFactory initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSessionFactory.getConfiguration().addMapper(GResultMapMapper.class);
        return sqlSessionFactory;
    }

    /**
     * 返回HashMap
     * 查询语句将所有的列映射到 HashMap 的键上，resultType 属性指定
     */
    @Test
    public void selectResultMap() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            HashMap hashMap = mapper.selectById("1");
            System.out.println(hashMap);
        }
    }

    /**
     * mybatis resultType 指定POJO对象
     * BlogDb对象字段与数字库字段一一对应
     */
    @Test
    public void selectToDBJavaBean() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogDb blogDb = mapper.selectToDBJavaBean("1");
            System.out.println(blogDb);
        }
    }

    /**
     * 使用别名指定数据类型
     */
    @Test
    public void selectToJavaBeanWithTypeAlias() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogDb blogDb = mapper.selectToJavaBeanWithTypeAlias("1");
            System.out.println(blogDb);
        }
    }

    /**
     * mybatis 使用 as 将数据库字段对应成POJO字段
     */
    @Test
    public void selectResultTypeToBlog() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            Blog blog = mapper.selectResultTypeToBlog("1");
            System.out.println(blog);
        }
    }

    /**
     * ResultMapper 数据库字段映射成POJO字段
     */
    @Test
    public void selectResultMapperToBlog() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            Blog blog = mapper.selectResultMapperToBlog("1");
            System.out.println(blog);
        }
    }

    /**
     * ResultMapper 返回复杂类型BlogInfo
     * BlogInfo 中包含一个构造函数和两个对象和一个集合
     * constructor - 用于在实例化类时，注入结果到构造方法中
     *      idArg - ID 参数；标记出作为 ID 的结果可以帮助提高整体性能
     *      arg - 将被注入到构造方法的一个普通结果
     * id – 一个 ID 结果；标记出作为 ID 的结果可以帮助提高整体性能
     * result – 注入到字段或 JavaBean 属性的普通结果
     * association – 一个复杂类型的关联；许多结果将包装成这种类型
     * 嵌套结果映射 – 关联本身可以是一个 resultMap 元素，或者从别处引用一个
     * collection – 一个复杂类型的集合
     * 嵌套结果映射 – 集合本身可以是一个 resultMap 元素，或者从别处引用一个
     * discriminator – 使用结果值来决定使用哪个 resultMap
     *      case – 基于某些值的结果映射
     *      嵌套结果映射 – case 本身可以是一个 resultMap 元素，因此可以具有相同的结构和元素，或者从别处引用一个
     */
    @Test
    public void selectResultMapperToBlogInfo() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogInfo blog = mapper.selectBlogInfo("1");
            System.out.println(blog);
        }
    }

/* ResultMap constructor */

    /**
     * ResultMapper-constructor 将结果注入构造方法
     *
     * constructor - 用于在实例化类时，注入结果到构造方法中
     *      idArg - ID 参数；标记出作为 ID 的结果可以帮助提高整体性能
     *      arg - 将被注入到构造方法的一个普通结果
     *
     * 对于多个参数的构造函数，有两种方式指定参数
     * 1.第一种（需要考虑顺序）：构造函数参数根据其数据类型和xml constructor指定的数据类型的顺序对应
     *  构造函数：BlogConstructor(Integer id,String name,String book)
     * xml:
     *     <constructor>
     *       <idArg column="ID" javaType="int"/>     对应构造函数的id
     *       <arg column="BOOK"  javaType="String"/> 对应构造函数的name（第一个String类型）
     *       <arg column="NAME"  javaType="String"/> 对应构造函数的book（第二个String类型）
     *     </constructor>
     * 2.第二种（不需要考虑顺序）在构造函数中使用@param("")指定参数名，同时在xml <constructor> 使用 name属性指定参数名，则可以指定对应参数
     * 构造函数：BlogConstructor(@Param("id") Integer id,@Param("name") String name,@Param("book") String book)
     *  xml:
     *      <constructor>
     *          <idArg column="ID"  name="id"/>   对应构造函数的id
     *          <arg column="BOOK"  name="book"/> 对应构造函数的book
     *          <arg column="NAME"  name="name"/> 对应够找函数的name
     *      </constructor>
     */
    @Test
    public void selectResultMapperConstructor() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogConstructor blog = mapper.selectBlogToConstructor("1");
            System.out.println(blog);
        }
    }

/* ResultMap Association*/

    /**
     *  Association 001
     *  mybatis ResultMapper Association(关联)
     *  关联一个User对象
     *  <association property="user" javaType="com.chen.mybatis.demo3.pojo.User">
     *       <id property="userId" column="USER_ID"/>
     *       <result property="userName" column="USER_NAME"/>
     *       <result property="mobile" column="MOBILE"/>
     *  </association>
     *  -- property 指定关联的字段
     *  -- javaType 关联对象的数据类型
     *  -- id 和 result 元素都将一个列的值映射到一个简单数据类型（String, int, double, Date 等）的属性或字段。
     *  -- id 元素表示的结果将是对象的标识属性，这会在比较对象实例时用到。 这样可以提高整体的性能，尤其是进行缓存和嵌套结果映射（也就是连接映射）的时候
     */
    @Test
    public void selectResultMapperAssociation() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogAssociation blog = mapper.selectBlogToAssociation("1");
            System.out.println(blog);
        }
    }

    /**
     *  Association 002
     * 关联的嵌套 Select 查询
     * <association>
     *    -- select ：用于加载复杂类型属性的映射语句的 ID，它会从 column 属性指定的列中检索数据，作为参数传递给目标 select 语句。 具体请参考下面的例子。注意：在使用复合主键的时候，你可以使用 column="{prop1=col1,prop2=col2}" 这样的语法来指定多个传递给嵌套 Select 查询语句的列名。这会使得 prop1 和 prop2 作为参数对象，被设置为对应嵌套 Select 语句的参数。
     *    -- column ：数据库中的列名，或者是列的别名。注意：在使用复合主键的时候，你可以使用 column="{prop1=col1,prop2=col2}" 这样的语法来指定多个传递给嵌套 Select 查询语句的列名。这会使得 prop1 和 prop2 作为参数对象，被设置为对应嵌套 Select 语句的参数。
     *    -- fetchType : 可选的。有效值为 lazy 和 eager。 指定属性后，将在映射中忽略全局配置参数 lazyLoadingEnabled(延迟加载)，使用属性的值。
     *
     *  使用 Association select 查询，先查询blog信息，再将blog userid信息作为参数传入Association 的select中，查询User信息并赋值到User对象中
     *  这种方式虽然很简单，但在大型数据集或大型数据表上表现不佳。这个问题被称为“N+1 查询问题”。 概括地讲，N+1 查询问题是这样子的：
     *  -- 你执行了一个单独的 SQL 语句来获取结果的一个列表（就是“+1”）。
     *  -- 对列表返回的每条记录，你执行一个 select 查询语句来为每条记录加载详细信息（就是“N”）。
     *  对此，如果查询数据量大的话，执行的sql语句是翻倍的，很多的，会导致很慢
     *  所以，可以使用延迟加载（fetchType）,当使用到user对象取值的时候，才会触发Association中的select查询语句(像文章详情就可以使用延时加载);todo:断点的时候还是会查出来
     */
    @Test
    public void selectResultMapperAssociationInnerSelect() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogAssociation blog = mapper.selectBlogToAssociationInnerSelect("1");
            System.out.println(blog.getId());
            System.out.println(blog.getUser());
        }
    }


    /**
     *  Association 003
     * 关联 ColumnPrefix属性的使用
     * columnPrefix ：行前缀
     * 指定ResultMap column与sql返回字段名的关系。也就是：ColumnPrefix + ResultMap column = sql字段名
     * 使用场景：
     *  一个ResultMap中存在多个Association,且association使用同一个ResultMap(Column一样),则此时想要获取正确的结果，则需要使用ColumnPrefix属性
     */
    @Test
    public void selectResultMapperAssociationWithColumnPrefix() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogAssociationColumnPrefix blog = mapper.selectBlogToAssociationWithColumnPrefix("1");
            System.out.println(blog);
        }
    }

    /**
     * Association 004
     * mybatis 执行多条语句并通过ResultSet关联的多结果集
     * mybatis-config url 设置allowMultiQueries=true开启运行执行多条语句
     *
     *   <association property="blogInfoDb" javaType="com.chen.mybatis.demo3.pojo.BlogInfoDb" resultSet="blogInfo" column="ID" foreignColumn="BLOG_ID" fetchType="lazy">
     *       <id property="blogInfoId" column="BLOG_INFO_ID"/>
     *       <result property="blogId" column="BLOG_ID"/>
     *       <result property="detail" column="DETAIL"/>
     *   </association>
     *   -- association resultSet 指定用于加载复杂类型的结果集名字。
     *   -- column 当使用多个结果集时，该属性指定结果集中用于与 foreignColumn 匹配的列（多个列名以逗号隔开），以识别关系中的父类型与子类型（一般是父结果集的主建）
     *   -- foreignColumn 指定外键对应的列名，指定的列将与父类型中 column 的给出的列进行匹配。
     *
     */
    @Test
    public void selectResultMapWithResultSets() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogInfo blog = mapper.selectResultMapWithResultSets("1");
            System.out.println(blog);

        }
    }

/* ResultMap Collection*/

    /**
     * ResultMap Collection learn001
     * Collection ResultMap  用于返回一个集合
     */
    @Test
    public void selectResultMapCollection() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogInfo blog = mapper.selectResultMapCollection("1");
            System.out.println(blog);
        }
    }


    /**
     * ResultMap Collection 嵌套select查询
     * 跟Association基本一样
     * Collection属性
     * -- ofType ： 用于指定集合内部对象的数据类型，入List<Blog>,则ofType为Blog类型
     * -- javaType：collection 的javatype一般都是集合类型，如：ArrayList，HashSet等，一般不需要指定，mybatis可以推断出来
     */
    @Test
    public void selectResultMapCollectionWithSelect() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogInfo blog = mapper.selectResultMapCollectionWithSelect("1");
            System.out.println(blog);
        }
    }

    /**
     * ResultMap Collection ResultSet多查询语句，返回多结果集
     * 与 Association  ResultSet 一样。
     */
    @Test
    public void selectResultMapCollectionWithResultSet() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogInfo blog = mapper.selectResultMapCollectionWithResultSet("1");
            System.out.println(blog);
        }
    }

/*Mybatis Discriminator 鉴别器*/

    /**
     * Mybatis Discriminator 鉴别器
     *
     * <discriminator javaType="String" column="IS_PAY">
     *    <case value="1" resultMap="payResultMap"/>
     *    <case value="2" resultMap="xxResultMap"/>
     *    <case value="3" resultMap="xxxResultMap"/>
     * </discriminator>
     *
     * -- 有时候，一个数据库查询可能会返回多个不同的结果集（但总体上还是有一定的联系的）。 鉴别器（discriminator）元素就是被设计来应对这种情况的，
     * -- 另外也能处理其它情况，例如类的继承层次结构。
     * --  鉴别器的概念很好理解——它很像 Java 语言中的 switch 语句。
     *
     * -- 一个鉴别器的定义需要指定 column 和 javaType 属性。column 指定了 MyBatis 查询被比较值的地方。
     *
     */
    @Test
    public void selectResultMapDiscriminator() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogDiscriminator blog = mapper.selectResultMapDiscriminator("1");
            System.out.println(blog);
        }
    }

/*    Mybatis 自动映射
*  -- 在简单的场景下，MyBatis 可以为你自动映射查询结果。但如果遇到复杂的场景，你需要构建一个结果映射。
*
*  -- 当自动映射查询结果时，MyBatis 会获取结果中返回的列名并在 Java 类中查找相同名字的属性（忽略大小写）。
*       -- 这意味着如果发现了 ID 列和 id 属性，MyBatis 会将列 ID 的值赋给 id 属性。
*
*  -- 通常数据库列使用大写字母组成的单词命名，单词间用下划线分隔；而 Java 属性一般遵循驼峰命名法约定。
*       -- 为了在这两种命名方式之间启用自动映射，需要将 mapUnderscoreToCamelCase 设置为 true。
*
* */

    /**
     * Mybatis 自动映射
     * 自动映射等级
     *  -- NONE - 禁用自动映射。仅对手动映射的属性进行映射。(只有ResultMap中有配置的才会映射)（此时javabean需要一个构造函数，不然报错：Do not know how to create an instance of class）
     *  -- PARTIAL - 对除在内部定义了嵌套结果映射（也就是连接的属性）以外的属性进行映射
     *  -- FULL - 自动映射所有属性。
     *  1.全局配置 ： configuration 配置：autoMappingBehavior属性
     *  2.局部配置：ResultMap 中 配置autoMapping 可以局部开启/关闭自动映射
     *
     *  本demo中，通过切换setAutoMappingBehavior的值进行测试
     *  -- NONE与其他的区别可以通过BlogInfo 的name字段看出
     *  -- PARTIAL 与 FULL 的区别可以通过BlogInfo.name 和 BlogInfo.USER.name 的值看出
     */
    @Test
    public void mybatisAutoMapper() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setAutoMappingBehavior(AutoMappingBehavior.NONE);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogInfo blog = mapper.selectBlogAutoMapperTest("1");
            System.out.println(blog);
        }
    }

/* Mybatis cache */

    /**
     * Mybatis chache
     * -- 映射语句文件中的所有 select 语句的结果将会被缓存。
     * -- 映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。
     * -- 缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
     * -- 缓存不会定时进行刷新（也就是说，没有刷新间隔）。
     * -- 缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。
     * -- 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。
     *
     * <cache
     *       eviction="FIFO"
     *       flushInterval="60000"
     *       size="512"
     *       readOnly="true"/>
     * -- 这个更高级的配置创建了一个 FIFO 缓存，每隔 60 秒刷新，最多可以存储结果对象或列表的 512 个引用，而且返回的对象被认为是只读的，因此对它们进行修改可能会在不同线程中的调用者产生冲突。
     *
     * -- 可用的清除策略有(默认的清除策略是 LRU)：
     *      LRU – 最近最少使用：移除最长时间不被使用的对象。
     *      FIFO – 先进先出：按对象进入缓存的顺序来移除它们。
     *      SOFT – 软引用：基于垃圾回收器状态和软引用规则移除对象。
     *      WEAK – 弱引用：更积极地基于垃圾收集器状态和弱引用规则移除对象。
     * -- flushInterval（刷新间隔）
     *      属性可以被设置为任意的正整数，设置的值应该是一个以毫秒为单位的合理时间量。
     *      默认情况是不设置，也就是没有刷新间隔，缓存仅仅会在调用语句时刷新。
     * -- size（引用数目
     *      属性可以被设置为任意正整数，要注意欲缓存对象的大小和运行环境中可用的内存资源。默认值是 1024。
     * -- readOnly（只读）
     *      属性可以被设置为 true 或 false。只读的缓存会给所有调用者返回缓存对象的相同实例。 因此这些对象不能被修改。这就提供了可观的性能提升。
     *      而可读写的缓存会（通过序列化）返回缓存对象的拷贝。 速度上会慢一些，但是更安全，因此默认值是 false。
     * -- 二级缓存是事务性的。这意味着，当 SqlSession 完成并提交时，或是完成并回滚，但没有执行 flushCache=true 的 insert/delete/update 语句时，缓存会获得更新。
     */
    @Test
    public void mybatisCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setAutoMappingBehavior(AutoMappingBehavior.NONE);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GResultMapMapper mapper = sqlSession.getMapper(GResultMapMapper.class);
            BlogInfo blog = mapper.selectBlogAutoMapperTest("1");
            System.out.println(blog);
        }
    }
}
