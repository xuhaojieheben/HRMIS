package com.core.library;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
public class Page implements IPage{
	@Override
	public StringBuffer SqlWhereStr(Object obj) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		// TODO 自动生成的方法存根
		int currPage = 0;
		int pageSize = 0;
		StringBuffer sqlLimit = null;
		StringBuffer sqlWhere = new StringBuffer(" where 1 = 1");
		//获取父类(具备当前页和每页显示数量的page参数类)
		Class<?> superClass = obj.getClass().getSuperclass();
		//获取父类名称
		String objSuper = superClass.getName();
		//获取父类字段s
		Field[] superFields = superClass.getDeclaredFields();
		if(superFields.length > 0) {
			Object _super = Class.forName(objSuper).newInstance();
			Method method = null;
			for(Field f : superFields) {
				f.setAccessible(true);
				String where = "";
				String key = f.getName();
				method = _super.getClass().getMethod("get" + key);
				if(key.toUpperCase().equals("INDEXPAGE") || key.toUpperCase().equals("CURRPAGE")) {
					currPage = (int)method.invoke(obj);
				}else if(key.toUpperCase().equals("PAGECOUNT") || key.toUpperCase().equals("PAGENUMBER") || key.toUpperCase().equals("PAGESIZE")){
					pageSize = (int)method.invoke(obj);
				}else if(key.toUpperCase().equals("ISDEL")){
					where = String.format(" and %s = 0 ", key);
					sqlWhere.append(where);
				}else if(key.toUpperCase().equals("CREATEDATE") || key.toUpperCase().equals("CREATETIME")){
					//where = String.format(" and {0} = 0", key);
					//sqlWhere.append(" and  ");
				}
				f.setAccessible(false);
			}
		}
		
		if(currPage >=1 && pageSize >= 1) {
			String objSimple = obj.getClass().getSimpleName();
/*			SELECT *
			  FROM TABLE1
			 WHERE ROWNUM <= 20
			MINUS
			SELECT * FROM TABLE1 WHERE ROWNUM <= 10;*/
			sqlLimit = new StringBuffer();
			sqlLimit.append(" AND ROWNUM <= " + pageSize + " MINUS SELECT * FROM " + objSimple + " WHERE ROWNUM <= " + (currPage - 1) * pageSize);
			//sqlLimit.append(" limit " + (currPage - 1) * pageSize + "," + pageSize);
			//获取object对象的子类即传过来的类
			
			//实例化子类
			Object simple = Class.forName(classPath + objSimple).newInstance();
			//获取子类所有字段
			Field[] fields = obj.getClass().getDeclaredFields();
			if(fields.length > 0) {
				Method method = null;
				//遍历字段
				for(Field f : fields) {
					f.setAccessible(true);
					String where = "";
					String key = f.getName();
					method = simple.getClass().getMethod("get" + key);
					Object _value = method.invoke(obj);
					if(_value != null && !_value.equals("")) {
						//判断每个字段的类型根据类型生成对应的查询语句如字符串用模糊查询语句拼装װ
						if(f.getType().equals(int.class)) {
							//添加自定义特性读取后可以知道用等于还是大于现在默认先用等于
							if(!key.toUpperCase().equals("ID")) {
								where = String.format(" and %1$s = %2$d", key, (int)_value);
							}
						}
						if(f.getType().equals(String.class)) {
							//添加自定义特性读取后可以知道用等于还是大于现在默认先用等于
							where = String.format(" and %1$s like %2$s", key, "'%" + _value.toString() + "%'");
						}
						if(f.getType().equals(double.class)) {
							//添加自定义特性读取后可以知道用等于还是大于现在默认先用等于
							where = String.format(" and %1$s = %2$f", key, (double)_value);
						}
						sqlWhere.append(where);
					}
					f.setAccessible(false);
				}
			}	
		}
		return sqlWhere.append(sqlLimit);
	}
}
