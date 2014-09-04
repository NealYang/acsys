package com.acsys.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import ognl.DefaultTypeConverter;

/**
 * @author Nealy
 * @date Aug 25, 2014
 */
public class DateConverter extends DefaultTypeConverter {
	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		try {
			if (toType == Date.class) {
				// 因为在Struts2会把表单传过来的非字符串数据转换为String[],所以这里得取第一个值
				String dataStr = ((String[]) value)[0];
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				return format.parse(dataStr);
			} else if (toType == String.class) {
				String dataStr = ((Date) value).toString();
				return dataStr;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			if (toType == Date.class) {
				return new Date();
			} else if (toType == String.class) {
				return "";
			}
		}
		return null;
	}
}