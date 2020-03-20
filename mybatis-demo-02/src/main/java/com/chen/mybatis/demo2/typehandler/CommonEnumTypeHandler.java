package com.chen.mybatis.demo2.typehandler;

import com.chen.mybatis.demo2.emuns.BlogTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 〈普通自定义枚举TypeHandler〉
 * 指定具体的枚举类进行类型转化，具有局限性
 *
 * @author chenmingjun
 * @create 2020.1.14
 * @company
 */
public class CommonEnumTypeHandler extends BaseTypeHandler<BlogTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BlogTypeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.getCode());
    }

    @Override
    public BlogTypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return BlogTypeEnum.getEnum(rs.getString(columnName));
    }

    @Override
    public BlogTypeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return BlogTypeEnum.getEnum(rs.getString(columnIndex));
    }

    @Override
    public BlogTypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return BlogTypeEnum.getEnum(cs.getString(columnIndex));
    }
}
