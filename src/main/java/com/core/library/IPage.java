package com.core.library;
import java.lang.reflect.InvocationTargetException;
public interface IPage {
	final String classPath = "model.sysmodel.entity.";
	StringBuffer SqlWhereStr(Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, NoSuchFieldException;
}
