package com.componentscan.unity;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import com.core.library.Page;
import model.sysmodel.entity.Sys_User;

@Intercepts({@Signature( type= StatementHandler.class,  method = "prepare",  args = {Connection.class,Integer.class})})
public class SqlInterceptor implements Interceptor{
	//ÿҳ��ʾ����Ŀ��
    private int pageSize;
    //��ǰ��ʵ��ҳ��
    private int currPage;
    @Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO �Զ����ɵķ������
		//��ȡStatementHandler��Ĭ����RoutingStatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //��ȡstatementHandler��װ��
        MetaObject MetaObjectHandler = SystemMetaObject.forObject(statementHandler);

        //������������
        while (MetaObjectHandler.hasGetter("h")) {
            Object obj = MetaObjectHandler.getValue("h");
            MetaObjectHandler = SystemMetaObject.forObject(obj);
        }

        while (MetaObjectHandler.hasGetter("target")) {
            Object obj = MetaObjectHandler.getValue("target");
            MetaObjectHandler = SystemMetaObject.forObject(obj);
        }

        //��ȡ���Ӷ���
        //Connection connection = (Connection) invocation.getArgs()[0];


        //object.getValue("delegate");  ��ȡStatementHandler��ʵ����

        //��ȡ��ѯ�ӿ�ӳ��������Ϣ
        MappedStatement mappedStatement = (MappedStatement) MetaObjectHandler.getValue("delegate.mappedStatement");
        String mapId = mappedStatement.getId();

        //statementHandler.getBoundSql().getParameterObject();

        //������.ByPage��β�����󣬷�ҳ���ܵ�ͳһʵ��
        if (mapId.matches(".+ByPage$")) {
            //��ȡ�������ݿ����ʱ���������handler
            ParameterHandler parameterHandler = (ParameterHandler) MetaObjectHandler.getValue("delegate.parameterHandler");
            //��ȡ����ʱ�Ĳ���
            //Map<String, Object> paraObject = (Map<String, Object>) parameterHandler.getParameterObject();
            //Ҳ����������ȡ
            //paraObject = (Map<String, Object>) statementHandler.getBoundSql().getParameterObject();
            Object paramObject = parameterHandler.getParameterObject();

			Page page = new Page();
            //�������ƺ���service�����õ�map�е�����һ��
            //currPage = Integer.parseInt(paramObject.get("currPage").toString());
            //pageSize = Integer.parseInt(paramObject.get("pageSize").toString());

            String sql = (String) MetaObjectHandler.getValue("delegate.boundSql.sql");
            //Ҳ����ͨ��statementHandlerֱ�ӻ�ȡ
            //sql = statementHandler.getBoundSql().getSql();
            
            //������ҳ���ܵ�sql���
            String limitSql = page.SqlWhereStr(paramObject).toString();
            sql = sql.trim();
            limitSql = sql + limitSql;
            System.out.println(limitSql);
            //��������ɵķ�ҳsql��丳ֵ����'delegate.boundSql.sql'
            MetaObjectHandler.setValue("delegate.boundSql.sql", limitSql);
        }
        //����ԭ����ķ�������������������һ��
        return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO �Զ����ɵķ������
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO �Զ����ɵķ������
		String limit1 = properties.getProperty("limit", "20");
        this.pageSize = Integer.valueOf(limit1);
        properties.getProperty("dbType", "mysql");
	}
}
