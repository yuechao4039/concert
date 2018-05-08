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

public class AddRequestVelocity {
    public void createEntityTemplate(String tableName, File enFile) {

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        Template template = ve.getTemplate("AddRequest.vm");
        VelocityContext ctx = new VelocityContext();
        /**
         * 包名
         */
        ctx.put(PackageEntity.PackageQualifiedNameKey, new PackageEntity().getQualifiedName(tableName));
        /**
         * 类名
         */
        ctx.put(ClassNameEntity.CLASS_NAME, new ClassNameEntity().getClassNameByTableName(tableName));
        /**
         * 属性
         */
        ctx.put(ColEntity.ColumnsKey, new ColEntity().getColsByTableName(tableName));
        /**
         * 引入java.sql.Date;
         */
        ctx.put("hasDateType", new ColEntity().hasDateType(tableName));

        StringWriter sw = new StringWriter();

        try (FileWriter fw = new FileWriter(enFile) ) {
            template.merge(ctx, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        t.merge(ctx, sw);
//        System.out.println(sw.toString());
    }

    public static void main(String[] args) {
        new AddRequestVelocity().createEntityTemplate("sm_user", null);
    }



}

