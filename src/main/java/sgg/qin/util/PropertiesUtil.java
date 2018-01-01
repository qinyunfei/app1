package sgg.qin.util;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** 
 * 说明：Properties工具类
 * 创建人：xiaoqin
 * 修改时间：2017年9月16日
 * @version
 */
public class PropertiesUtil {
	

	/** 
	 *根据Key读取Value
	 */
    public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try {
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
            pps.load(in);
            String value = pps.getProperty(key);
            return value;
            
        }catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
    }
    
    //读取Properties的全部信息
    public static Map<String, String> GetAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
        pps.load(in);
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        Map<String, String> map =new HashMap<>();
        
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);  
            map.put(strKey, strValue);
         }
        return map;
    }
    
    //写入Properties信息
    public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
        //从输入流中读取属性列表（键和元素对） 
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        //以适合使用 load 方法加载到 Properties 表中的格式，  
        //将此 Properties 表中的属性列表（键和元素对）写入输出流  
        pps.store(out, "Update " + pKey + " name");
    }    
}
