/**
 * 生成数据库sql分页接口
 */
package com.core.library;

/**
 * @author john
 * 各个数据库方言
 */
public interface Dialect {
	public static enum DBType {
        MYSQL {
            public String getValue() {
                return "mysql";
            }
        },
        MSSQL {
            public String getValue() {
                return "sqlserver";
            }
        },
        ORACLE {
            public String getValue() {
                return "oracle";
            }
        }
	}
	
	/**
     * @description 获取分页SQL
     * @author john
     * @create 
     * @param sql
     *            原始查询SQL
     * @param offset
     *            开始记录索引（从零开始）
     * @param pageSize
     *            每页记录大小
     * @return 返回数据库相关的分页SQL语句
	 * @throws Throwable 
     */
    public abstract String SetPageSql(Page<?> page, StringBuffer sql) throws Throwable;
}
