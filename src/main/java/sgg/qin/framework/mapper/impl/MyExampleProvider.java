package sgg.qin.framework.mapper.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class MyExampleProvider extends MapperTemplate{

    public MyExampleProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }
	   /**
     * 根据Example查询
     *
     * @param ms
     * @return
     */
    public String selectByExampleToStr(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        //设置返回值类型为string
        ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(ms.getConfiguration(), "BaseMapperResultMap-Inline", String.class, new ArrayList<ResultMapping>(), null);
        MetaObject metaObject = SystemMetaObject.forObject(ms);
        resultMaps.add(inlineResultMapBuilder.build());
        metaObject.setValue("resultMaps", Collections.unmodifiableList(resultMaps));
        
        //将返回值修改为实体类型
        //setResultType(ms, String.class);
        
        StringBuilder sql = new StringBuilder("SELECT ");
        if(isCheckExampleEntityClass()){
            sql.append(SqlHelper.exampleCheck(entityClass));
        }
        sql.append("<if test=\"distinct\">distinct</if>");
        //支持查询指定列
        sql.append(SqlHelper.exampleSelectColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.exampleWhereClause());
        sql.append(SqlHelper.exampleOrderBy(entityClass));
        sql.append(SqlHelper.exampleForUpdate());
        return sql.toString();
    }

    public String selectAllToMap(MappedStatement ms){
         Class<?> entityClass = getEntityClass(ms);
       
        //设置返回值类型为map
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(ms.getConfiguration(), "BaseMapperResultMap-Inline", Map.class, new ArrayList<ResultMapping>(), null);
        MetaObject metaObject = SystemMetaObject.forObject(ms);
        resultMaps.add(inlineResultMapBuilder.build());
        metaObject.setValue("resultMaps", Collections.unmodifiableList(resultMaps));
        
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.orderByDefault(entityClass));
        return sql.toString();
    }
    
    public String insertList(MappedStatement ms) {
    		Class<?> entityClass = getEntityClass(ms);
        //获取表的各项属性
        EntityTable table = EntityHelper.getEntityTable(entityClass);
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");
        sql.append(table.getName());
        sql.append("(");
        boolean first = true;
        for (EntityColumn column : table.getEntityClassColumns()) {
            if(!first) {
                sql.append(",");
            }
            sql.append(column.getColumn());
            first = false;
        }
        sql.append(") values ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("(");
        first = true;
        for (EntityColumn column : table.getEntityClassColumns()) {
            if(!first) {
                sql.append(",");
            }
            sql.append("#{record.").append(column.getProperty()).append("}");
            first = false;
        }
        sql.append(")");
        sql.append("</foreach>");
        System.out.println(sql.toString());
        return sql.toString();
    }
    
    
}
