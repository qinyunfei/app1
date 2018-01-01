package sgg.qin.framework.mapper;

import tk.mybatis.mapper.common.Mapper;

public interface MyMapper<T> extends 
			Mapper<T>, 
			SelectByExampleMapperToStr<T>,
			SelectAllToMap<T>,
			MyInsertListMapper<T>
{}
