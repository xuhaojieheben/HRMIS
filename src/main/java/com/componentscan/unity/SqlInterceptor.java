package com.componentscan.unity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import com.core.library.Dialect;
import com.core.library.Page;
import com.iflytek.mybatis.page.dialect.OracleDialect;

@Intercepts({@Signature( type= StatementHandler.class,  method = "prepare",  args = {Connection.class,Integer.class})})
public class SqlInterceptor implements Interceptor{
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTORFACTORY = new DefaultReflectorFactory();

    @Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO 自动生成的方法存根
    	StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
    	MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTORFACTORY);
        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        //Connection configuration = (Connection) invocation.getArgs()[0];
    	Dialect.Type databaseType = null;
        String _handleName = "";
        //分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object,
                    DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTORFACTORY);
        }
        //分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object,
                    DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTORFACTORY);
        }
        try {
            databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
        } catch (Exception e) {
            
        }if (databaseType == null) {
            throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : " + configuration.getVariables().getProperty("dialect"));
        }
        try {
        	_handleName = configuration.getVariables().getProperty("pageSqlRex");
        } catch (Exception e) {
            
        }if (_handleName == null) {
            throw new RuntimeException("the value of the pageSqlRex property in configuration.xml is not defined : " + configuration.getVariables().getProperty("pageSqlRex"));
        }
        //获取查询接口映射的相关信息
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        String mapId = mappedStatement.getId();
        if (mapId.matches(_handleName)) {
        	Dialect dialect = null;
            switch (databaseType) {  
            case MYSQL:  
                //dialect = new OracleDialect();
                break;  
            case MSSQL:  
                //dialect = new OracleDialect();
                break;  
            case ORACLE:
                dialect = new OracleDialect();
                break;  
            default:  
                dialect = new OracleDialect();
            }
        	BoundSql boundSql = statementHandler.getBoundSql();
        	StringBuffer sql = new StringBuffer(boundSql.getSql());
        	Object object = boundSql.getParameterObject();
        	if(object instanceof Page<?>) {
        		Page<?> page = (Page<?>) object;
        		Connection connection = (Connection) invocation.getArgs()[0];
                //采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
                /*metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);*/
        		this.SetTotalRecord(page, mappedStatement, connection);
        		String pageSql = dialect.SetPageSql(page, sql);
        		
        		metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
        		//ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
        	}
        }
        //调用原对象的方法，进入责任链的下一级
        return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO 自动生成的方法存根
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的  
	    // 次数  
	    if (target instanceof StatementHandler) {  
	        return Plugin.wrap(target, this);  
	    } else {
	        return target;  
	    }  
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO 自动生成的方法存根
		//String prop1 = properties.getProperty("prop1");
	    //String prop2 = properties.getProperty("prop2");
	    //System.out.println(prop1 + "------" + prop2);
	}
	
	/**
	 * 查询总纪录数
     * @param sql             SQL语句
     * @param connection      数据库连接
     * @param mappedStatement mapped
     * @param parameterObject 参数
     * @param boundSql        boundSql
     * @return 总记录数
     */
    private void SetTotalRecord(final Page<?> page, final MappedStatement mappedStatement,
    		final Connection connection) throws SQLException {
    	BoundSql boundSql = mappedStatement.getBoundSql(page);  
        //获取到我们自己写在Mapper映射语句中对应的Sql语句  
        String sql = boundSql.getSql();  
        //通过查询Sql语句获取到对应的计算总记录数的sql语句 
        
        String countSql = "select count(1) from (" + sql + ")";  
        //通过BoundSql获取对应的参数映射  
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
        //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。  
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);  
        //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象  
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);  
        //通过connection建立一个countSql对应的PreparedStatement对象。  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        try {  
            pstmt = connection.prepareStatement(countSql);  
            //通过parameterHandler给PreparedStatement对象设置参数  
            parameterHandler.setParameters(pstmt);  
            //之后就是执行获取总记录数的Sql语句和获取结果了。
            rs = pstmt.executeQuery();  
            if (rs.next()) {  
               int totalRecord = rs.getInt(1);
               //给当前的参数page对象设置总记录数
               page.setTotalRecord(totalRecord);  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
               if (rs != null)  
                   rs.close();  
                if (pstmt != null)  
                   pstmt.close();  
            } catch (SQLException e) {  
               e.printStackTrace();  
            }  
        }
    }

}
