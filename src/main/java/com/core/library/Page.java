package com.core.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.iflytek.mybatis.page.dialect.PageBase;

public class Page<T> extends PageBase{
	private final String ORDERBY = " ORDER BY ";
    private int totalRecord;//�ܼ�¼��
    private int totalPage;//��ҳ��
    private List<T> results;//��Ӧ�ĵ�ǰҳ��¼
    private String orderBy = "";
    private Map<String, Object> params = new HashMap<String, Object>();//�����Ĳ������ǰ�����װ��һ��Map����

    public int getTotalRecord() {
       return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
       this.totalRecord = totalRecord;
       //��������ҳ����ʱ��������Ӧ����ҳ�������������Ŀ�����мӷ�ӵ�и��ߵ����ȼ������������Բ������š�
       int totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
       this.setTotalPage(totalPage);
    }

    public int getTotalPage() {
       return totalPage;
    }

    public void setTotalPage(int totalPage) {
       this.totalPage = totalPage;
    }

    public List<T> getResults() {
       return results;
    }

    public void setResults(List<T> results) {
       this.results = results;
    }

    public Map<String, Object> getParams() {
       return params;
    }

    public void setParams(Map<String, Object> params) {
       this.params = params;
    }

	public String getOrderBy() {
		// SQL���ˣ���ֹע��
        String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        if (sqlPattern.matcher(orderBy).find()) {
            return "";
        }
        return ORDERBY + orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
       builder.append("Page [currPage=").append(currPage).append(", pageSize=")
              .append(pageSize).append(", results=").append(results).append(
                     ", totalPage=").append(totalPage).append(
                     ", totalRecord=").append(totalRecord).append("]");
       return builder.toString();
    }
}
