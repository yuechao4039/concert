package com.hll.concert.common;

/**
 * @author yuechao 2018/4/29
 */
public class ClassNameEntity {
    public static String ClassNameQualifiedNameKey = "className";


    public String getClassNameByTableName(String tableName) {
        String[] tbls = tableName.split(Entity.underscore);
        StringBuilder sb = new StringBuilder();

        for (String str : tbls) {
            sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
        }

        return sb.toString();
    }
}
