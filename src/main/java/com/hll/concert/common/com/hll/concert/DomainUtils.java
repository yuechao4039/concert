package com.hll.concert.common.com.hll.concert;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;
import java.util.Properties;

public class DomainUtils {
    public String entityName = "";
    public String className = "";
    public String packageName = "";
    public String packagePath = "";
    public VelocityEngine ve = null;

    public DomainUtils(String tableName, Class clazz){
        this.entityName = getEntityName(tableName);
        this.className = getClassName(tableName);
        packagePath = clazz.getPackage().getName();
        this.packageName = "package " + packagePath;
        init();
    }
    public VelocityEngine getEngine(){
        return ve;
    }
    public void init(){
        ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();
    }
    public String getClassName(String tableName) {
        String[] tbls = tableName.split("_");
        StringBuilder sb = new StringBuilder();

        for (String str : tbls) {
            sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
        }
        return sb.toString();
    }

    public String getEntityName(String tableName) {
        String[] tbls = tableName.split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tbls.length; i++) {
            if(i == 0)
                sb.append(tbls[i]);
            else{
                sb.append(tbls[i].substring(0, 1).toUpperCase()).append(tbls[i].substring(1));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){

        Properties pros = System.getProperties();
        String path = pros.getProperty("user.dir") + "src\\main\\java\\" + "com.hll.concert.common.com.hll.concert.service".replace(".", "\\") + File.pathSeparator ;
        System.out.println(path);
    }
    public File generateJavaFile() {
        Properties pros = System.getProperties();
        String parentPath = pros.getProperty("user.dir") + "\\src\\main\\java\\" + packagePath.replace(".", "\\").replace(";", "") + File.separator ;
        File parentDir = new File(parentPath);
        System.out.println(parentDir);
        File javaFile = new File(parentDir, className + "Service.java");
        System.out.println(javaFile);
        if (!parentDir.exists())
           return null;

        try {
            if(!javaFile.exists())
                javaFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaFile;
    }
}
