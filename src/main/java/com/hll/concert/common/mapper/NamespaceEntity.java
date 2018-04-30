package com.hll.concert.common.mapper;

import com.hll.concert.common.Entity;

/**
 * @author yuechao 2018/4/30
 */
public class NamespaceEntity {


    public String namespace(String tableName) {
        String[] arr = tableName.split(Entity.underscore);
        StringBuilder sb = new StringBuilder(Entity.basePackage);
        StringBuilder daoName = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                sb.append(arr[i]);

                daoName.append(arr[i].substring(0, 1).toUpperCase()).append(arr[i].substring(1)).append("Dao");
            } else {
                sb.append(arr[i]).append(".");

                daoName.append(arr[i].substring(0, 1).toUpperCase()).append(arr[i].substring(1));
            }
        }


        return sb.toString();
    }


    public static void main(String[] args) {
        // com.hll.concert.sm.role.SmRoleDao
        System.out.println(new NamespaceEntity().namespace("sm_role"));;
    }
}
