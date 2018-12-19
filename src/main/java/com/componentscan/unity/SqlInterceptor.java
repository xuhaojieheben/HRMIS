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
		// TODO �Զ����ɵķ������
    	StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
    	MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTORFACTORY);
        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        //Connection configuration = (Connection) invocation.getArgs()[0];
    	Dialect.Type databaseType = null;
        String _handleName = "";
        //������������(����Ŀ������ܱ�������������أ��Ӷ��γɶ�δ���ͨ�����������ѭ�����Է������ԭʼ�ĵ�Ŀ����)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object,
                    DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTORFACTORY);
        }
        //�������һ����������Ŀ����
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
        //��ȡ��ѯ�ӿ�ӳ��������Ϣ
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
                //���������ҳ�󣬾Ͳ���Ҫmybatis���ڴ��ҳ�ˣ����������������������
                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
                /*metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);*/
        		this.SetTotalRecord(page, mappedStatement, connection);
        		String pageSql = dialect.SetPageSql(page, sql);
        		
        		metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
        		//ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
        	}
        }
        //����ԭ����ķ�������������������һ��
        return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO �Զ����ɵķ������
		// ��Ŀ������StatementHandler����ʱ���Ű�װĿ���࣬����ֱ�ӷ���Ŀ�걾��,����Ŀ�걻�����  
	    // ����  
	    if (target instanceof StatementHandler) {  
	        return Plugin.wrap(target, this);  
	    } else {
	        return target;  
	    }  
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO �Զ����ɵķ������
		//String prop1 = properties.getProperty("prop1");
	    //String prop2 = properties.getProperty("prop2");
	    //System.out.println(prop1 + "------" + prop2);
	}
	
	/**
	 * ��ѯ�ܼ�¼��
     * @param sql             SQL���
     * @param connection      ���ݿ�����
     * @param mappedStatement mapped
     * @param parameterObject ����
     * @param boundSql        boundSql
     * @return �ܼ�¼��
     */
    private void SetTotalRecord(final Page<?> page, final MappedStatement mappedStatement,
    		final Connection connection) throws SQLException {
    	BoundSql boundSql = mappedStatement.getBoundSql(page);  
        //��ȡ�������Լ�д��Mapperӳ������ж�Ӧ��Sql���  
        String sql = boundSql.getSql();  
        //ͨ����ѯSql����ȡ����Ӧ�ļ����ܼ�¼����sql��� 
        
        String countSql = "select count(1) from (" + sql + ")";  
        //ͨ��BoundSql��ȡ��Ӧ�Ĳ���ӳ��  
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
        //����Configuration����ѯ��¼����Sql���countSql������ӳ���ϵparameterMappings�Ͳ�������page������ѯ��¼����Ӧ��BoundSql����  
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);  
        //ͨ��mappedStatement����������page��BoundSql����countBoundSql����һ�������趨������ParameterHandler����  
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);  
        //ͨ��connection����һ��countSql��Ӧ��PreparedStatement����  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        try {  
            pstmt = connection.prepareStatement(countSql);  
            //ͨ��parameterHandler��PreparedStatement�������ò���  
            parameterHandler.setParameters(pstmt);  
            //֮�����ִ�л�ȡ�ܼ�¼����Sql���ͻ�ȡ����ˡ�
            rs = pstmt.executeQuery();  
            if (rs.next()) {  
               int totalRecord = rs.getInt(1);
               //����ǰ�Ĳ���page���������ܼ�¼��
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
