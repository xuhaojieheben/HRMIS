package com.iflytek.mybatis.page.dialect;

public class PageBase {
	public int currPage = 1;//页码，默认是第一页
	public int pageSize = 20;//每页显示的记录数，默认是20
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
}
