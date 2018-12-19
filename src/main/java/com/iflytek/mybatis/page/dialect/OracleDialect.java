package com.iflytek.mybatis.page.dialect;

import com.core.library.Dialect;
import com.core.library.Page;

public class OracleDialect implements Dialect{

	@Override
	public String SetPageSql(Page<?> page, StringBuffer sqlBuffer) {
		// TODO 自动生成的方法存根
		//计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的  
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
