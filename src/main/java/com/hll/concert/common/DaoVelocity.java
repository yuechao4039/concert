package com.hll.concert.common;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DaoVelocity {
    public static void createEntityTemplate(String tableName, File file) {


        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        Template t = ve.getTemplate("Dao.vm");

        String packageName = new PackageEntity().getQualifiedName(tableName);

        VelocityContext ctx = new VelocityContext();
        ctx.put("packageName", packageName);
        ctx.put("className", getTableName(tableName));
//        StringWriter sw = new StringWriter();
//        t.merge(ctx, sw);
//        System.out.println(sw);
        try (FileWriter fw = new FileWriter(file); ) {
            t.merge(ctx, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String getTableName(String tableName) {
        String[] tbls = tableName.split("_");
        StringBuilder sb = new StringBuilder();

        for (String str : tbls) {
            sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
        }
        return sb.toString();
    }


}
