package com.iflytek.mybatis.page.dialect;

import com.core.library.Dialect;
import com.core.library.Page;

public class OracleDialect implements Dialect{

	@Override
	public String SetPageSql(Page<?> page, StringBuffer sqlBuffer) {
		// TODO �Զ����ɵķ������
		//�����һ����¼��λ�ã�Oracle��ҳ��ͨ��rownum���еģ���rownum�Ǵ�1��ʼ��  
	    int offset = (page.getCurrPage() - 1) * page.getPageSize() + 1;  
	    sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());  
	    sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);
	    String orderBy = page.getOrderBy();
	    if(orderBy != "") {
	    	sqlBuffer.append(orderBy);
	    }
		return sqlBuffer.toString();
	}
}
