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

public class MapperVelocity {
    public void createEntityTemplate(String tableName, File enFile) {

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        Template t = ve.getTemplate("mapper.vm");
        VelocityContext ctx = new VelocityContext();

        ctx.put(PackageEntity.PackageQualifiedNameKey, new PackageEntity().generatePackageQualifiedName(tableName));

        ctx.put(NamespaceEntity.NAMESPACE, new NamespaceEntity().namespace(tableName));

        ctx.put("entityName", Character.toLowerCase(new ClassNameEntity().getClassNameByTableName(tableName).charAt(0)) + new ClassNameEntity().getClassNameByTableName(tableName).substring(1));

        ctx.put(ClassNameEntity.CLASS_NAME, new ClassNameEntity().getClassNameByTableName(tableName));
        ctx.put("tableName", tableName);
        ctx.put("insert", MapperUtil.insert(tableName));
        ctx.put("update", MapperUtil.update(tableName));
        ctx.put("delete", MapperUtil.delete(tableName));
        ctx.put("resultMapList", MapperUtil.getResultMap(tableName));
        ctx.put("columns", MapperUtil.getColumns(tableName));

        try (FileWriter fw = new FileWriter(enFile) ) {
            t.merge(ctx, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        StringWriter sw = new StringWriter();
//        t.merge(ctx, sw);
//        System.out.println(sw.toString());
    }

    public static void main(String[] args) {
        new MapperVelocity().createEntityTemplate("sm_role", null);
    }


}

