package com.chen.mybatis.demo2.typehandler;

import com.chen.mybatis.demo2.emuns.DisplayedEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 〈泛型枚举TypeHandler〉
 *  适用于实现了DisplayedEnum的枚举
 * @author chenmingjun
 * @create 2020.1.16
 * @company
 */
public class GenericEnumTypeHandler extends BaseTypeHandler<DisplayedEnum> {

    private Class<DisplayedEnum> type;

    public GenericEnumTypeHandler(Class<DisplayedEnum> displayedEnum) {
        if (displayedEnum == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = displayedEnum;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DisplayedEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.getCode());
    }

    @Override
    public DisplayedEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convert(rs.getString(columnName));
    }

    @Override
    public DisplayedEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convert(rs.getString(columnIndex));
    }

    @Override
    public DisplayedEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convert(cs.getString(columnIndex));
    }


        private DisplayedEnum convert(String code){
        DisplayedEnum[] objs = type.getEnumConstants();
        for(DisplayedEnum em: objs){
            if (Objects.equals(em.getCode(), code)) {
                return em;
            }
        }
        return null;
    }
}
