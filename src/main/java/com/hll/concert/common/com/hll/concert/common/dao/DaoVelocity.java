package com.hll.concert.common.com.hll.concert.common.dao;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

public class DaoVelocity {
    public static String PACKAGE = "package ";
    public static void main(String[] args){

        String tableName = "sm_role";
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        Template t = ve.getTemplate("Dao.vm");

        String packageName = DaoVelocity.PACKAGE + DaoVelocity.class.getPackage().getName();
        System.out.println(packageName);
        VelocityContext ctx = new VelocityContext();
        ctx.put("packageName", packageName);
        ctx.put("className", getTableName(tableName));
        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        System.out.println(sw);


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
