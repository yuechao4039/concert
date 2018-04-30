package com.hll.concert.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class PackageEntity {
    public static String PackageQualifiedNameKey = "packageQualifiedName";
    static String[] packageNames = new String[]{"package com.hll.concert."};


    public List<String> generatePackageQualifiedName(String tableName) {
        StringTokenizer st = new StringTokenizer(tableName, "_");

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
        new PackageEntity().generatePackageQualifiedName("sm_role").forEach(x -> {
            System.out.println(x);
        });
    }

}