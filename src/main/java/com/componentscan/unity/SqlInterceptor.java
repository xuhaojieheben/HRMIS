package com.componentscan.unity;

import java.sql.Connection;
import java.util.Properties;


import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.aspectj.bridge.ReflectionFactory;

import com.core.library.Dialect;



@Intercepts({@Signature( type= StatementHandler.class,  method = "prepare",  args = {Connection.class,Integer.class})})
public class SqlInterceptor implements Interceptor{
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTORFACTORY = new DefaultReflectorFactory();

    @Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO 自动生成的方法存根
    	StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
    	BoundSql boundSql = statementHandler.getBoundSql();
    	System.out.println(boundSql);
    	MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTORFACTORY);
    	
        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        Dialect.Type databaseType = null;
        try {
            databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
        } catch (Exception e) {
            
        }
        if (databaseType == null) {
            throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : " + configuration.getVariables().getProperty("dialect"));
        }
        //调用原对象的方法，进入责任链的下一级
        return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO 自动生成的方法存根
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO 自动生成的方法存根
		String prop1 = properties.getProperty("prop1");
	       String prop2 = properties.getProperty("prop2");
	       System.out.println(prop1 + "------" + prop2);

		//System.out.println("setProperties");
		//String pageSqlRegex = properties.getProperty("pageSqlReg");

        //System.out.println(pageSqlRegex);
        //properties.getProperty("dbType", "mysql");
	}

}
