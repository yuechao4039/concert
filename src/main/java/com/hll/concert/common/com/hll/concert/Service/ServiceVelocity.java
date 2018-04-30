package com.hll.concert.common.com.hll.concert.Service;

import com.hll.concert.common.com.hll.concert.DomainUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class ServiceVelocity {

    static String tableName = "sm_role";
    public static void main(String[] args){

        DomainUtils domain = new DomainUtils(tableName, ServiceVelocity.class);
        Template t = domain.getEngine().getTemplate("service.vm");

        VelocityContext ctx = new VelocityContext();
        ctx.put("className", domain.className);
        ctx.put("packageName", domain.packageName);
        ctx.put("entityName", domain.entityName);

        StringWriter sw = new StringWriter();

        t.merge(ctx, sw);
        try {
            FileWriter fw = new FileWriter(domain.generateJavaFile());
            t.merge(ctx, fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sw.toString());

    }
}
