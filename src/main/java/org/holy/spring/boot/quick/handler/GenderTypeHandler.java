package org.holy.spring.boot.quick.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.holy.spring.boot.quick.constants.type.GenderType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 性别类型转换
 * @author holy
 * @version 1.0.0
 * @date 2019/9/4 23:06
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(String.class)
public class GenderTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        GenderType genderType = GenderType.valueOf(parameter);
        ps.setInt(i, genderType.getCode());
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        GenderType genderType = GenderType.valueOf(code);
        return genderType.getValue();
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        GenderType genderType = GenderType.valueOf(code);
        return genderType.getValue();
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        GenderType genderType = GenderType.valueOf(code);
        return genderType.getValue();
    }
}
