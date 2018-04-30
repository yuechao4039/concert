package com.hll.concert.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yuechao 2018/4/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColEntity {
    public static String ColumnsKey = "columns";
    public static String colSQL = "SELECT COLUMN_NAME, ORDINAL_POSITION, DATA_TYPE, COLUMN_COMMENT  FROM information_schema.COLUMNS WHERE table_name = 'sm_role' AND table_schema = 'portal' " +
            "ORDER BY ORDINAL_POSITION";

    private String columnName;
    private Integer ordinalPosition;
    private String dataType;
    private String columnComment;

    public static void main(String[] args) {
        new ColEntity().getColsByTableName("sm_role").forEach(System.out::println);
    }


    public List<ColEntity> getColsByTableName(String tableName) {
        List<ColEntity> list = new ArrayList<>();
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(colSQL); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                int ordinalPosition = rs.getInt("ORDINAL_POSITION");
                String dataType = rs.getString("DATA_TYPE");
                String columnComment = rs.getString("COLUMN_COMMENT");
                ColEntity en = new ColEntityBuilder()
                        .columnName(mapUnderscoreToCamelCase(columnName))
                        .ordinalPosition(ordinalPosition)
                        .dataType(javaType(dataType))
                        .columnComment(columnComment)
                        .build();
                list.add(en);
            }
        } catch (Exception e) {

        }

        return list;
    }

    private String javaType(String dataType) {
        if (dataType.equals("int")) {
            return Integer.class.getSimpleName();
        }
        if (dataType.equals("char")) {
            return String.class.getSimpleName();
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

    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://cfssdoc.suwusoft.com:3306/portal", "doc", "Doc@1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
