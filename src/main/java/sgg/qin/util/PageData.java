package sgg.qin.util;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;


import org.apache.commons.beanutils.BeanUtils;
/** 
 * 说明：自定义Map
 * 创建人：xiaoqin
 * 修改时间：2017年9月16日
 * @version
 */
public class PageData<K, V>  extends HashMap<K, V>{
	
	private static final long serialVersionUID = 1L;
	
	Map<K, V> map = null;

	public PageData() {
		map = new HashMap<K, V>();
		
	}
	

	/**
	 * 将PageData对象转换为javabean
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> T getbean(Class<T> clazz)   {
		T obj=null;
		try {
			obj = clazz.newInstance();
			BeanUtils.populate(obj, (Map<String, ? extends Object>) this);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * 将Object对象转换为PageData并返回PageData
	 */
	public PageData<String,String> getnewPageData(Object obj) throws Exception {
		PageData<String,String> pageData = new PageData<String,String>();
		 Map<String, String> map = BeanUtils.describe(obj);
		 for (Map.Entry<String, String> entry : map.entrySet()) {
			 if (!entry.getKey().equals("class")) {
				 pageData.put(entry.getKey(), entry.getValue());
			}
			
		}
		return pageData;
	}
	
	/**
	 * 将Object转换为PageData叠加到PageData
	 */
	public void getBaenToPageData(Object obj) throws Exception {
		 Map<String, String> map = BeanUtils.describe(obj);
		 for (Map.Entry<String, String> entry : map.entrySet()) {
			 if (!entry.getKey().equals("class")) {
				 this.put((K)entry.getKey(), (V)entry.getValue());
			}
			
		}
		
	}
	
	/**
	 * 将PageData转全部转为<key,value>
	 */
	public PageData<String,Object> getAllToPageData() throws Exception {
		PageData<String,Object> pageData = new PageData<String,Object>(); 
		for (Map.Entry<K, V> entry : this.entrySet()) {
			V value = entry.getValue();
			if (value instanceof Integer || value instanceof String || value instanceof Double || value instanceof Float
				|| value instanceof Long || value instanceof Long || value instanceof Boolean
				|| value instanceof Date) {
				pageData.put((String)entry.getKey(), value);
			}else {
				pageData.getBaenToPageData(value);
			}	
		}
		return pageData;
		
	}
	
	
	
	
	

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		Set<Entry<K, V>> entrySet = this.entrySet();
		for (Entry<K, V> entry : entrySet) {

			 String str=Objects.hashCode(entry.getKey())+"lol"+Objects.hashCode(entry.getValue());
			 result = prime * result + str.hashCode();
		}
		return result;
	}




	@Override
	public V put(K key, V value) {
		return  map.put(key, value);
	}
	@Override
    public V get(Object key) {
        return map.get(key);
    }
    @Override
    public Set<Map.Entry<K,V>> entrySet() {
      
        return map.entrySet();
    }
    @Override
    public V remove(Object key) {
    	return map.remove(key);
    }
    @Override
    public void clear() {
    	map.clear();
    }
    @Override
    public int size() {
        return map.size();
    }
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }
    @Override
    public boolean containsValue(Object value) {
      
        return map.containsValue(value);
    }
    @Override
    public Set<K> keySet() {
      
        return map.keySet();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
      
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return map.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return map.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
    
        return map.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
      
        return map.replace(key, value);
    }

    @Override
    public V computeIfAbsent(K key,
                             Function<? super K, ? extends V> mappingFunction) {
      
        return map.computeIfAbsent(key, mappingFunction);
    }

    public V computeIfPresent(K key,
                              BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
       
        return map.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key,
                     BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        
        return map.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value,
                   BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
       
        return map.merge(key, value, remappingFunction);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
      	map.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
      map.replaceAll(function);
    }
	
	
	
	
	
	
	
	

	
}
