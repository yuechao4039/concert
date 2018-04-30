package com.hll.concert.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuechao 2018/4/30
 */
public class DBUtil {

    public static String COLUMN_SQL = "SELECT COLUMN_NAME, ORDINAL_POSITION, DATA_TYPE, COLUMN_COMMENT, TABLE_NAME, COLUMN_KEY  FROM information_schema.COLUMNS WHERE table_name = 'tableName' AND table_schema = 'portal' " +
            "ORDER BY ORDINAL_POSITION";

    public static String TABLE_SQL = "SELECT TABLE_NAME FROM `information_schema`.`TABLES` WHERE TABLE_SCHEMA = 'portal' ";


    public static String getColumnSQL(String tableName) {
        return COLUMN_SQL.replace("tableName", tableName);
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://cfssdoc.suwusoft.com:3306/portal", "doc", "Doc@1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static List<TableEntity> getAllTables() {
        List<TableEntity> tables = new ArrayList<>();
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(TABLE_SQL); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TableEntity entity = new DBUtil().new TableEntity();
                entity.setTableName(rs.getString("TABLE_NAME"));
                tables.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tables;
    }

    @Data
    @NoArgsConstructor
    public class TableEntity {

        private String tableName;
    }
}


