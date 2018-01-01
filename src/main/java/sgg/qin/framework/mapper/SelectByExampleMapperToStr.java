package sgg.qin.framework.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import sgg.qin.framework.mapper.impl.MyExampleProvider;
import tk.mybatis.mapper.provider.ExampleProvider;

public interface SelectByExampleMapperToStr<T>  {
	
    /**
     * 根据Example条件进行查询 返回String的集合类型
     *
     * @param example
     * @return
     */
    @SelectProvider(type = MyExampleProvider.class, method = "dynamicSQL")
    List<String> selectByExampleToStr(Object example);

}
