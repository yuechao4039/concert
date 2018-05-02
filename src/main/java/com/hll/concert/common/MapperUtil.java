package com.hll.concert.common;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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

    public static List<Map<String, String>> getResultMap(String tableName){
        List<Map<String, String>> resultMapList = new ArrayList<>();

        List<ColEntity> list = new ColEntity().getColsByTableName(tableName);
        Predicate<String> predicate = s->!s.equalsIgnoreCase("id");
        list.forEach(x->{
            if(predicate.test(x.getColumnName())) {
                Map<String, String> map = new HashMap<>();
                map.put("column", x.getUnderscoreColumnName());
                map.put("property", x.getColumnName());
                resultMapList.add(map);
            }
        });
            return  resultMapList;
    }

    public static String getColumns(String tableName){
        StringBuilder sb = new StringBuilder();
        List<ColEntity> list = new ColEntity().getColsByTableName(tableName);
        list.forEach(x->
            sb.append(", ").append(x.getUnderscoreColumnName())
        );
        return sb.substring(2).toString();
    }

    public static String getProperty(String column){
        String c = column;
        if(c.indexOf("_") == -1){
            return c;
        }
        StringBuilder sb = new StringBuilder();
        String[] arr = c.split("_");
        for (int i = 0; i < arr.length; i++) {
            if(i>0){
                sb.append(arr[i].substring(0, 1)
                        .toUpperCase())
                        .append(arr[i].substring(1));
            }else {
                sb.append(arr[i]);
            }
        }
       return sb.toString();
    }
    public static void main(String[] args) {

        String str = ", aaa_a_aa";
        System.out.println(getProperty(str));
        System.out.println(getResultMap("sm_role"));
        System.out.println(getColumns("sm_role"));
//        System.out.println(MapperUtil.insert("sm_role"));
//        System.out.println(MapperUtil.update("sm_role"));
//        System.out.println(MapperUtil.delete("sm_role"));
    }
}
