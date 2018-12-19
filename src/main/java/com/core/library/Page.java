package com.core.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> {
	private int currPage = 1;//ҳ�룬Ĭ���ǵ�һҳ
    private int pageSize = 20;//ÿҳ��ʾ�ļ�¼����Ĭ����20
    private int totalRecord;//�ܼ�¼��
    private int totalPage;//��ҳ��
    private List<T> results;//��Ӧ�ĵ�ǰҳ��¼
    private Map<String, Object> params = new HashMap<String, Object>();//�����Ĳ������ǰ�����װ��һ��Map����

    public int getCurrPage() {
       return currPage;
    }

    public void setCurrPage(int currPage) {
       this.currPage = currPage;
    }

    public int getPageSize() {
       return pageSize;
    }

    public void setPageSize(int pageSize) {
       this.pageSize = pageSize;
    }

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
