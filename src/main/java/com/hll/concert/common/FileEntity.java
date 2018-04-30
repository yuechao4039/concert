package com.hll.concert.common;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuechao 2018/4/30
 */
public class FileEntity {


    public File mkdir(String tableName) {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        File file = new File(path + File.separator + Entity.basePath, pursuePath(tableName));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public String pursuePath(String tableName) {
        String[] arr = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]).append(File.separator);
            }
        }
        return sb.toString();
    }
    static List<String> vms = new ArrayList<>(Arrays.asList("Entity.java", "Dao.java",
            "AddRequest.java", "DeleteRequest.java",
            "FindByIdRequest.java", "OnlyResp.java", "QueryRequest.java",
            "UpdateRequest.java", "Vo.java", "QueryResp.java", "Service.java"));
    public List<FileType> createJavaFile(String tableName) {
        File file = mkdir(tableName);
        StringBuilder sb = new StringBuilder();
        String[] arr = tableName.split(Entity.underscore);
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i].substring(0, 1).toUpperCase()).append(arr[i].substring(1));
        }

        List<String> list = vms.stream().map(x ->
             sb.toString() + x
        ).collect(Collectors.toList());

        List<FileType> typeList = new ArrayList<>();

        list.forEach(x -> {
            FileType fileType = new FileType();
            File xFile = new File(file, x);
            if (!xFile.exists()) {
                try {
                    xFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            fileType.setFile(xFile);
            fileType.setFileType(x);
            typeList.add(fileType);
        });
        return typeList;
    }

    public static void main(String[] args) {


    }
    @Data
    public class FileType {
        private String fileType;
        private File file;
    }
}
