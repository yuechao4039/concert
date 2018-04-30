package com.hll.concert.common.mapper;

import com.hll.concert.common.ClassNameEntity;
import com.hll.concert.common.ColEntity;
import com.hll.concert.common.PackageEntity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

public class MapperVelocity {
    public static void main(String[] args) {

        String tableName = "sm_role";

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

        ctx.put(ClassNameEntity.ClassNameQualifiedNameKey, new ClassNameEntity().getClassNameByTableName(tableName));

        ctx.put(ColEntity.ColumnsKey, new ColEntity().getColsByTableName(tableName));

        StringWriter sw = new StringWriter();

        t.merge(ctx, sw);

        System.out.println(sw.toString());
    }



}

