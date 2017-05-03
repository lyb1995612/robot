package com.csjbot.robot.base.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author lzl
 * 
 */

public class ValidateUtils {
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		boolean isEmpty = false;
		if (obj == null) {
			isEmpty = true;
		} else if (obj instanceof String) {
			isEmpty = ((String) obj).trim().isEmpty();
		} else if (obj instanceof Collection) {
			isEmpty = (((Collection) obj).size() == 0);
		} else if (obj instanceof Map) {
			isEmpty = ((Map) obj).size() == 0;
		} else if (obj.getClass().isArray()) {
			isEmpty = Array.getLength(obj) == 0;
		}
		return isEmpty;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> c) {
		return !isEmpty(c);
	}

	/**
	 * 去重
	 * 
	 * @param list
	 */
	public static int removeDuplicate(List<Object> list) {
		int count = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
					++count;
				}
			}
		}	
		
		return count;
	}
	
	/**
	 * 去重
	 * 
	 * @param list
	 */
	public static int removeDuplicate(Object[] objs) {
		List<Object> list = Arrays.asList(objs);		
		int count = removeDuplicate(list);		
		objs = list.toArray();	
		
		return count;
	}
}
