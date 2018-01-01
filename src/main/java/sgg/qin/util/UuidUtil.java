package sgg.qin.util;

import java.util.UUID;
/** 
 * 说明：常用工具
 * 创建人：xiaoqin
 * 修改时间：2017年9月16日
 * @version
 */
public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
}