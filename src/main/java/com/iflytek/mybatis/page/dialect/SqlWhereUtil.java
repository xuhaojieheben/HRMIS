package com.iflytek.mybatis.page.dialect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.custom.annotation.FieldSqlWhereAnnotation;

public class SqlWhereUtil {
	public static String getSqlWhereByPageBase(PageBase pageBase) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuffer sqlWhere = new StringBuffer(" where 1 = 1");
		Class<?> pBaseClass = pageBase.getClass();
		Field[] pBaseFields = pBaseClass.getDeclaredFields();
		Object _pBase = null;
		try {
			_pBase = Class.forName(pBaseClass.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(pBaseFields.length > 0) {
			Method method = null;
			for(Field f : pBaseFields) {
				f.setAccessible(true);
				String key = f.getName();
				try {
					method = _pBase.getClass().getMethod("get" + key);
					//System.out.println("get" + key);
					Object _value = method.invoke(pageBase);
					String _dbFiledName = getDbFieldName(f);
					String dbFiledName = _dbFiledName == "" ? key : _dbFiledName;
					if(_value != null) {
						if(f.getType().equals(int.class)) {
							//添加自定义特性读取后可以知道用等于还是大于现在默认先用等于
							//if(!key.toUpperCase().equals("ID")) {
								sqlWhere.append(String.format(" and %1$s = %2$d", dbFiledName, (int)_value));
							//}
						}
						if(f.getType().equals(String.class)) {
							if(_value.toString().trim() != "") {
							//添加自定义特性读取后可以知道用等于还是大于现在默认先用等于
								sqlWhere.append(String.format(" and %1$s like %2$s", dbFiledName, "'%" + _value.toString() + "%'"));
							}
						}
						if(f.getType().equals(double.class)) {
							//添加自定义特性读取后可以知道用等于还是大于现在默认先用等于
							sqlWhere.append(String.format(" and %1$s = %2$f", dbFiledName, (double)_value));
						}
						if(f.getType().equals(Date.class)) {
							SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
							String edate = date.format(_value);
							if(key.indexOf("Begin") != -1) {
								sqlWhere.append(String.format(" and %1$s >= %2$s", dbFiledName, "to_date('" + edate + "','yyyy-mm-dd')"));
							}else if (key.indexOf("End") != -1) {
								sqlWhere.append(String.format(" and %1$s <= %2$s", dbFiledName, "to_date('" + edate + "','yyyy-mm-dd')"));
							}else {
								sqlWhere.append(String.format(" and %1$s = %2$s", dbFiledName, "to_date('" + edate + "','yyyy-mm-dd')"));
							}
						}
					}
				} catch (NoSuchMethodException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
		return sqlWhere.toString();
	};
	
	public static String getDbFieldName(Field field) {
		boolean fieldHasAnno = field.isAnnotationPresent(FieldSqlWhereAnnotation.class);  
	    if(fieldHasAnno){  
	    	FieldSqlWhereAnnotation fieldAnno = field.getAnnotation(FieldSqlWhereAnnotation.class);  
	        //输出注解属性
	        return fieldAnno.DbField();  
	    }
	    return "";
	}
}
