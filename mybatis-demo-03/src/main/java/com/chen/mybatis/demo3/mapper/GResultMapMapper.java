package com.chen.mybatis.demo3.mapper;

import com.chen.mybatis.demo3.pojo.*;

import java.util.HashMap;

/**
 * 〈ResultMap〉
 *
 * @author chenmingjun
 * @create 2020.2.18
 * @company
 */
public interface GResultMapMapper {

    HashMap selectById(String id);

    BlogDb selectToDBJavaBean(String id);

    BlogDb selectToJavaBeanWithTypeAlias(String id);

    Blog selectResultTypeToBlog(String id);

    Blog selectResultMapperToBlog(String id);

    BlogInfo selectBlogInfo(String id);

    BlogConstructor selectBlogToConstructor(String id);

    BlogAssociation selectBlogToAssociation(String id);

    BlogAssociation selectBlogToAssociationInnerSelect(String id);

    BlogAssociationColumnPrefix selectBlogToAssociationWithColumnPrefix(String id);

    BlogInfo selectResultMapWithResultSets(String id);

    BlogInfo selectResultMapCollection(String id);

    BlogInfo selectResultMapCollectionWithSelect(String id);

    BlogInfo selectResultMapCollectionWithResultSet(String id);

    BlogDiscriminator selectResultMapDiscriminator(String id);

    BlogInfo selectBlogAutoMapperTest(String id);
}
