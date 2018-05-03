package com.hll.concert.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuechao 2018/4/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColEntity {
    public static String ColumnsKey = "columns";

    private String underscoreColumnName;
    private String columnName;
    private List<String> annotations;
    private Integer ordinalPosition;
    private String dataType;
    private String columnComment;
    private String tableName;
    /**
     * 用于判断是否是主键
     */
    private String columnKey;

    public static void main(String[] args) {
        new ColEntity().getColsByTableName("sm_role").forEach(System.out::println);
    }


    public List<ColEntity> getKeysByTableName(String tableName) {
        return getColsByTableName(tableName).stream().filter(x -> x.getColumnKey().equals("PRI")).collect(Collectors.toList());
    }


    public List<ColEntity> getColsByTableName(String tableName) {
        List<ColEntity> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConn(); PreparedStatement ps = conn.prepareStatement(DBUtil.getColumnSQL(tableName)); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                String columnName = rs.getString("COLUMN_NAME");
                int ordinalPosition = rs.getInt("ORDINAL_POSITION");
                String dataType = rs.getString("DATA_TYPE");
                String columnComment = rs.getString("COLUMN_COMMENT");
                String tableNameDb = rs.getString("TABLE_NAME");
                String columnKey = rs.getString("COLUMN_KEY");
                ColEntity en = new ColEntityBuilder()
                        .underscoreColumnName(columnName)
                        .columnName(mapUnderscoreToCamelCase(columnName))
                        .ordinalPosition(ordinalPosition)
                        .dataType(javaType(dataType))
                        .columnComment(columnComment)
                        .annotations(annotations(dataType))
                        .tableName(tableNameDb)
                        .columnKey(columnKey)
                        .build();
                list.add(en);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private List<String> annotations(String dataType) {
        List<String> annotations = new ArrayList<>();
        if (dataType.equals("int") || dataType.equals("tinyint")) {
            annotations.add("@NotEmpty(message = \"不能为空\")");
        }
        if (dataType.equals("char") || dataType.equals("varchar")) {
            annotations.add("@NotNull(message = \"不能为空\")");
        }
        return annotations;
     }

    private String javaType(String dataType) {
        if (dataType.equals("int")) {
            return Integer.class.getSimpleName();
        }
        if (dataType.equals("char")) {
            return String.class.getSimpleName();
        }
        if (dataType.equals("varchar")) {
            return String.class.getSimpleName();
        }

        if (dataType.equals("tinyint")) {
            return Integer.class.getSimpleName();
        }
        if (dataType.equals("timestamp")) {
            return Timestamp.class.getSimpleName();
        }

        throw new RuntimeException("javatype");
    }

    private String mapUnderscoreToCamelCase(String columnName) {
        String[] arr = columnName.split("_");
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        if (arr.length == 1) {

        } else {
            for (int i = 1; i < arr.length; i++) {
                sb.append(arr[i].substring(0, 1).toUpperCase()).append(arr[i].substring(1));
            }
        }

        return sb.toString();
    }


}
