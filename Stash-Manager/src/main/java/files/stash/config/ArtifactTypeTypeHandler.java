package files.stash.config;

import files.stash.domain.entity.ArtifactType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis TypeHandler for converting ArtifactType enum to/from database string values.
 * 
 * This handler ensures proper conversion between the ArtifactType enum constants
 * and their string representations in the database.
 */
@MappedTypes(ArtifactType.class)
public class ArtifactTypeTypeHandler extends BaseTypeHandler<ArtifactType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ArtifactType parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setString(i, parameter.getDisplayName());
        }
    }

    @Override
    public ArtifactType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : ArtifactType.fromDisplayName(value);
    }

    @Override
    public ArtifactType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : ArtifactType.fromDisplayName(value);
    }

    @Override
    public ArtifactType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : ArtifactType.fromDisplayName(value);
    }
}
