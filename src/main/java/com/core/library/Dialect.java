/**
 * �������ݿ�sql��ҳ�ӿ�
 */
package com.core.library;

/**
 * @author john
 * �������ݿⷽ��
 */
public interface Dialect {
	public static enum Type {
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
     * @description ��ȡ��ҳSQL
     * @author john
     * @create 
     * @param sql
     *            ԭʼ��ѯSQL
     * @param offset
     *            ��ʼ��¼���������㿪ʼ��
     * @param pageSize
     *            ÿҳ��¼��С
     * @return �������ݿ���صķ�ҳSQL���
     */
    public abstract String getPageSql(String sql, int offset, int pageSize);
}