package com.hll.concert.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class PackageEntity {
    public static String PackageQualifiedNameKey = "packageName";
    static String[] packageNames = new String[]{"package ", Entity.basePackage};


    public String getQualifiedName(String tableName) {
        StringBuilder sb = new StringBuilder();
        generatePackageQualifiedName(tableName).forEach(x -> {
            sb.append(x);
        });
        return sb.toString();
    }

    public List<String> generatePackageQualifiedName(String tableName) {
        StringTokenizer st = new StringTokenizer(tableName, Entity.underscore);

        List<String> list = new ArrayList<>(Arrays.asList(packageNames));
        int length = st.countTokens();
        for (int i = 0; i < length; i++) {
            String token = st.nextToken();
            if (i == length - 1) {
                list.add(token + ";");
            } else {
                list.add(token + ".");
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new PackageEntity().getQualifiedName("contract"));
//        new PackageEntity().generatePackageQualifiedName("contract").forEach(x -> {
//            System.out.println(x);
//        });
    }

}