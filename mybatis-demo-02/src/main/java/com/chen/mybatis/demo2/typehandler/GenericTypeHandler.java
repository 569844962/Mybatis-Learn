package com.chen.mybatis.demo2.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * 〈泛型TypeHandler〉
 *
 * @author chenmingjun
 * @create 2020.1.8
 * @company
 */
public class GenericTypeHandler<E extends Collection> extends BaseTypeHandler<E> {

    private Class<E> type;

    public GenericTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null && parameter.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Object e : parameter) {
                stringBuilder.append(e);
                stringBuilder.append(";");
            }
            ps.setString(i, stringBuilder.toString());
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            E e = this.type.newInstance();
            String string = rs.getString(columnName);
            if (string != null) {
                String[] split = string.split(";");
                if (split.length == 0) {
                    return e;
                }
                for (int i = 0; i < split.length; i++) {
                    e.add(split[i]);
                }
            }
            return e;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            E e = this.type.newInstance();
            String string = rs.getString(columnIndex);
            if (string != null) {
                String[] split = string.split(";");
                if (split.length == 0) {
                    return e;
                }
                for (int i = 0; i < split.length; i++) {
                    e.add(split[i]);
                }
            }
            return e;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            E e = this.type.newInstance();
            String string = cs.getString(columnIndex);
            if (string != null) {
                String[] split = string.split(";");
                if (split.length == 0) {
                    return e;
                }
                for (int i = 0; i < split.length; i++) {
                    e.add(split[i]);
                }
            }
            return e;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
    }
}
