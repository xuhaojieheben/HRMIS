package com.iflytek.mybatis.page.dialect;

public class PageBase {
	public int currPage = 1;//ҳ�룬Ĭ���ǵ�һҳ
	public int pageSize = 20;//ÿҳ��ʾ�ļ�¼����Ĭ����20
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
