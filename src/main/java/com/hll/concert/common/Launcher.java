package com.hll.concert.common;




/**
 * @author yuechao 2018/4/30
 */
public class Launcher {


    public static void main(String[] args) {

        DBUtil.getAllTables().stream().filter(x ->
            x.getTableName().equals("sm_user")
        ).forEach(x -> {
            String tableName = x.getTableName();
            /**
             * 创建包目录
             */
            new FileEntity().createJavaFile(tableName).forEach(y -> {
                /**
                 * 生成entity
                 */
                if (y.getFileType().contains("Entity.java")) {
                    new EntityVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("Dao.java")) {
                    new DaoVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("AddRequest.java")) {
                    new AddRequestVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("DeleteRequest.java")) {
                    new DeleteRequestVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("FindByIdRequest.java")) {
                    new FindByIdRequestVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("OnlyResp.java")) {
                    new OnlyRespVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("QueryRequest.java")) {
                    new QueryRequestVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("UpdateRequest.java")) {
                    new UpdateRequestVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("Vo.java")) {
                    new VoVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("QueryResp.java")) {
                    new QueryRespVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("Service.java")) {
                    new ServiceVelocity().createEntityTemplate(tableName, y.getFile());
                }

                if (y.getFileType().contains("Mapper.xml")) {
                    new MapperVelocity().createEntityTemplate(tableName, y.getFile());
                }
            });
        });
    }
}
