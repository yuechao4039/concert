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
import java.io.StringWriter;

public class ServiceVelocity {


    public void createEntityTemplate(String tableName, File file) {

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        Template t = ve.getTemplate("Service.vm");

        String packageName = new PackageEntity().getQualifiedName(tableName);

        VelocityContext ctx = new VelocityContext();
        /**
         * 包名
         */
        ctx.put(PackageEntity.PackageQualifiedNameKey, new PackageEntity().getQualifiedName(tableName));
        /**
         * 类名
         */
        ctx.put(ClassNameEntity.CLASS_NAME, new ClassNameEntity().getClassNameByTableName(tableName));

        ctx.put("entityName", Character.toLowerCase(new ClassNameEntity().getClassNameByTableName(tableName).charAt(0)) + new ClassNameEntity().getClassNameByTableName(tableName).substring(1));

        if (null != file)
        try (FileWriter fw = new FileWriter(file)) {
            t.merge(ctx, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        StringWriter sw = new StringWriter();
//        t.merge(ctx, sw);
//        System.out.println(sw);

    }

    public static void main(String[] args) {
        new ServiceVelocity().createEntityTemplate("sm_user", null);
    }


}
