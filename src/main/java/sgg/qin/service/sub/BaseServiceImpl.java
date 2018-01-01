package sgg.qin.service.sub;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;


import sgg.qin.framework.mapper.MyMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @Description: 通用service实现类
 * @author: Qin YunFei
 * @date: 2017年11月24日 下午4:19:55
 * @version V1.0
 */
public class BaseServiceImpl<T> {

	// 使用spring4的泛型注入
	@Autowired
	private MyMapper<T> mapper;

	/**
	 * 通过反射获取子类确定的泛型类 获取Example对象
	 */
	public Example getExample() {
		// 1. 获取带泛型的父类类型
		Type type = this.getClass().getGenericSuperclass();

		// 2. 参数化类型
		ParameterizedType pt = (ParameterizedType) type;

		// 3. 获取真实参数数组
		Type[] types = pt.getActualTypeArguments();
		
		// 4.获取泛型类型
		Class cl = (Class) types[0];

		return new Example(cl);
	}

	/**
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 *
	 * @param record
	 * @return
	 */
	public T selectOne(T record) {

		return mapper.selectOne(record);
	}

	/**
	 * 根据实体中的属性值进行查询，查询条件使用等号
	 *
	 * @param record
	 * @return
	 */
	public List<T> select(T record) {

		return mapper.select(record);
	}

	/**
	 * 查询全部结果
	 *
	 * @return
	 */
	public List<T> selectAll() {

		return mapper.selectAll();
	}

	/**
	 * 根据实体中的属性查询总数，查询条件使用等号
	 *
	 * @param record
	 * @return
	 */
	public int selectCount(T record) {

		return mapper.selectCount(record);
	}

	/**
	 * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 *
	 * @param key
	 * @return
	 */
	public T selectByPrimaryKey(Object key) {

		return mapper.selectByPrimaryKey(key);
	}

	/**
	 * 根据主键字段查询总数，方法参数必须包含完整的主键属性，查询条件使用等号
	 *
	 * @param key
	 * @return
	 */
	public boolean existsWithPrimaryKey(Object key) {

		return mapper.existsWithPrimaryKey(key);
	}

	/**
	 * 保存一个实体，null的属性也会保存，不会使用数据库默认值
	 *
	 * @param record
	 * @return
	 */
	public int insert(T record) {

		return mapper.insert(record);
	}

	/**
	 * 保存一个实体，null的属性不会保存，会使用数据库默认值
	 *
	 * @param record
	 * @return
	 */
	public int insertSelective(T record) {

		return mapper.insertSelective(record);
	}

	/**
	 * 根据主键更新实体全部字段，null值会被更新
	 *
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(T record) {

		return mapper.updateByPrimaryKey(record);
	}

	/**
	 * 根据主键更新属性不为null的值
	 *
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(T record) {

		return mapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据实体属性作为条件进行删除，查询条件使用等号
	 *
	 * @param record
	 * @return
	 */
	public int delete(T record) {

		return mapper.delete(record);
	}

	/**
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 *
	 * @param key
	 * @return
	 */
	public int deleteByPrimaryKey(Object key) {

		return mapper.deleteByPrimaryKey(key);
	}

	/**
	 * 根据Example条件进行查询
	 *
	 * @param example
	 * @return
	 */
	public List<T> selectByExample(Object example) {

		return mapper.selectByExample(example);
	}

	/**
	 * 根据Example条件进行查询总数
	 *
	 * @param example
	 * @return
	 */
	public int selectCountByExample(Object example) {

		return mapper.selectCountByExample(example);
	}

	/**
	 * 根据Example条件删除数据
	 *
	 * @param example
	 * @return
	 */
	public int deleteByExample(Object example) {

		return mapper.deleteByExample(example);
	}

	/**
	 * 根据Example条件更新实体`record`包含的不是null的属性值
	 *
	 * @param record
	 * @param example
	 * @return
	 */
	public int updateByExample(T record, Object example) {

		return mapper.updateByExampleSelective(record, example);
	}

	/**
	 * 根据Example条件更新实体`record`包含的不是null的属性值
	 *
	 * @param record
	 * @param example
	 * @return
	 */
	public int updateByExampleSelective(T record, Object example) {

		return mapper.updateByExampleSelective(record, example);
	}

	/**
	 * 根据example条件和RowBounds进行分页查询
	 *
	 * @param example
	 * @param rowBounds
	 * @return
	 */
	public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {

		return mapper.selectByExampleAndRowBounds(example, rowBounds);
	}

	/**
	 * 根据实体属性和RowBounds进行分页查询
	 *
	 * @param record
	 * @param rowBounds
	 * @return
	 */
	public List<T> selectByRowBounds(T record, RowBounds rowBounds) {

		return mapper.selectByRowBounds(record, rowBounds);
	}

	/**
	 * 根据Example条件进行查询 获取String集合
	 *
	 * @param example
	 * @return
	 */
	public List<String> selectByExampleToStr(Object example) {
		// TODO Auto-generated method stub
		return mapper.selectByExampleToStr(example);
	}

	/**
	 * 查询全部结果 返回map
	 *
	 * @return
	 */
	public List<Map<String, Object>> selectAllToMap() {
		// TODO Auto-generated method stub
		return mapper.selectAllToMap();
	}

	/**
	 * 批量添加
	 * 
	 * @param recordList
	 * 		对象集合
	 */
	public int insertList(List<T> recordList) {
		// TODO Auto-generated method stub
		return mapper.insertList(recordList);
	}
}
