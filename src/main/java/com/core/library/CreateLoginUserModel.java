package com.core.library;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import model.sysmodel.entity.BaseModel;

public class CreateLoginUserModel {
	public static LoginUserModel Bind(BaseModel m) throws NumberFormatException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException, ClassNotFoundException {
		Class<? extends BaseModel> obj = m.getClass();
		Field[] fields = obj.getDeclaredFields();
		LoginUserModel _userModel = new LoginUserModel();
		//Field[] loginUserFields = LoginUserModel.class.getFields();
		Field[] loginUserFields = _userModel.getClass().getDeclaredFields();
		for(Field luf : loginUserFields) {
			luf.setAccessible(true);
			String key = luf.getName();
			//String type = luf.getGenericType().toString();
		    for (Field field : fields) {
		    	if(key == field.getName()) {
		    		field.setAccessible(true);
		    		Method setMethod = LoginUserModel.class.getMethod("set" + key, luf.getType());
		    		setMethod.invoke(_userModel, field.get(m));
		    	}
		    }
		}
		return _userModel;
	}
}
