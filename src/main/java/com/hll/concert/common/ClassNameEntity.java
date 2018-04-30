package com.hll.concert.common;

/**
 * @author yuechao 2018/4/29
 */
public class ClassNameEntity {
    public static String ClassNameQualifiedNameKey = "classNameQualifiedName";

    public static String classNameSuffix = "Entity";

    public String getClassNameByTableName(String tableName) {
        String[] tbls = tableName.split("_");
        StringBuilder sb = new StringBuilder();

        for (String str : tbls) {
            sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
        }
        sb.append(classNameSuffix);
        return sb.toString();
    }
}
