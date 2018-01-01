package sgg.qin.framework.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import sgg.qin.framework.mapper.impl.MyExampleProvider;

public interface MyInsertListMapper<T>  {
	
	/**
     * 批量插入，支持数据库自增字段，支持回写
     *
     * @param recordList
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = MyExampleProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);

}
