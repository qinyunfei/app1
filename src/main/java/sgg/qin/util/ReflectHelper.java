package sgg.qin.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

/** 
 * 说明：反射工具
 * 创建人：xiaoqin
 * 修改时间：2017年9月16日
 * @version
 */
public class ReflectHelper {
	
	
	/**
	 * 获取方法Method所有参数名称
	 * 
	 * @param Object
	 * @return
	 */
	public static String[] getParameterNames(Method method) {
		return new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
	}
	
	
	
	
	/**
	 * 获取方javabean的MetaObject对象(建议使用此方法进行反射操作)
	 * MetaObject是Mybatis提供的一个用于方便、优雅访问或设置对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常，同时它支持对JavaBean、Collection、Map三种类型对象的操作。
	 * 使用方式请百度:metaObject.getValue(属性名称 )   metaObject.setValu（属性名称,属性值） 可以一直及联获取或者设置
	 * @param Object
	 * @return
	 */
	public static MetaObject getMetaObject(Object object) {
		return SystemMetaObject.forObject(object);
	}
	
	
	/**
	 * 判断对象是否是基本数据类型或封装类
	 * 
	 * @param Object
	 * @return
	 */
	public static boolean isTypr(Object param) {

		if (param instanceof Integer || param instanceof String || param instanceof Double || param instanceof Float
				|| param instanceof Long || param instanceof Long || param instanceof Boolean
				|| param instanceof Date) {
			return true;
		}
		return false;
	}
	
	
	
	//以下方法建议停止使用改用本类的getParameterNames方法就可以了
	
	
	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	 @Deprecated 
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}
	
	/**
	 * 获取class对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	 @Deprecated 
	public static Field getFieldByFieldName(Class clazz, String fieldName) {
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	 @Deprecated 
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	 @Deprecated 
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	
	
	//获取当前方法的Method对象
	public static Method getCurrentMethod() {
        StackTraceElement[] yste = Thread.currentThread().getStackTrace();
        if (yste.length<2) {
            return null;
        }
        /**getMethodName**/
        String str="";
        for (int i = 0; i < yste.length; i++) {
            if(yste[i].getMethodName().equals("getCurrentMethod")){
                Class<?> cC=null;
                try {
                    cC=Class.forName(yste[i+1].getClassName());
                } catch (ClassNotFoundException e) {    
                		//e.printStackTrace();
                	}
                Method[] ym=cC.getMethods();
                str= yste[i+1].toString();
                str=str.substring(0, str.lastIndexOf('('));
 
                for (int j = 0; j < ym.length; j++) {
                    if (str.endsWith(ym[j].getName())){
                        return ym[j];
                    }
                }
            }
        }
        return null;
    }
}
