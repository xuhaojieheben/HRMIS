package com.core.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> {
	private int currPage = 1;//页码，默认是第一页
    private int pageSize = 20;//每页显示的记录数，默认是20
    private int totalRecord;//总记录数
    private int totalPage;//总页数
    private List<T> results;//对应的当前页记录
    private Map<String, Object> params = new HashMap<String, Object>();//其他的参数我们把它分装成一个Map对象

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
       //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
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
