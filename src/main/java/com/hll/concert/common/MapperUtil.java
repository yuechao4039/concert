package com.hll.concert.common;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author yuechao 2018/5/1
 */
public class MapperUtil {

    public static String insert(String tableName) {
        StringBuilder sb = new StringBuilder("insert into ");
        sb.append(tableName).append(" (");
        List<ColEntity> list = new ColEntity().getColsByTableName(tableName);
        for (int i = 0; i < list.size(); i++) {
            ColEntity x = list.get(i);
            if (!StringUtils.isEmpty(x.getColumnKey())) {
                continue;
            }
           if (i == list.size() - 1) {
                sb.append(x.getUnderscoreColumnName());
            } else {
                sb.append(x.getUnderscoreColumnName()).append(", ");
            }
        }
        sb.append(") values (");

        for (int i = 0; i < list.size(); i++) {
            ColEntity x = list.get(i);
            sb.append("#{").append(x.getColumnName()).append("}");
            if (i == list.size() - 1) {

            } else {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }



    public static String update(String tableName) {
        StringBuilder sb = new StringBuilder("update ");
        sb.append(tableName).append(" set ");
        List<ColEntity> list = new ColEntity().getColsByTableName(tableName);
        for (int i = 0; i < list.size(); i++) {
            ColEntity x = list.get(i);
            if (!StringUtils.isEmpty(x.getColumnKey())) {
                continue;
            }
            if (i == list.size() - 1) {
                sb.append(x.getUnderscoreColumnName()).append(" = ").append("#{").append(x.getColumnName()).append("}");
            } else {
                sb.append(x.getUnderscoreColumnName()).append(" = ").append("#{").append(x.getColumnName()).append("}, ");
            }
        }
        sb.append(" where ");

        for (int i = 0; i < list.size(); i++) {
            ColEntity x = list.get(i);
            if (StringUtils.isEmpty(x.getColumnKey())) {
                continue;
            }
            sb.append(x.getUnderscoreColumnName()).append(" = #{").append(x.getColumnName()).append("}");
        }

        return sb.toString();
    }

    public static String delete(String tableName) {
        StringBuilder sb = new StringBuilder("delete from ");
        sb.append(tableName).append(" where ");
        List<ColEntity> list = new ColEntity().getColsByTableName(tableName);
        for (int i = 0; i < list.size(); i++) {
            ColEntity x = list.get(i);
            if (StringUtils.isEmpty(x.getColumnKey())) {
                continue;
            }
            sb.append(x.getUnderscoreColumnName()).append(" = #{").append(x.getColumnName()).append("}");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(MapperUtil.insert("sm_role"));
        System.out.println(MapperUtil.update("sm_role"));
        System.out.println(MapperUtil.delete("sm_role"));
    }
}
