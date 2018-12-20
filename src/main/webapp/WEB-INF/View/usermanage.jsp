<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HRMIS</title>
<link rel="stylesheet" href="../bootstrap/css/font-awesome.min.css">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../bootstraptable/dist/bootstrap-table.css">
<script type="text/javascript" src="../static/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="../bootstraptable/dist/bootstrap-table.js"></script>
<script type="text/javascript" src="../bootstraptable/dist/locale/bootstrap-table-zh-CN.js"></script>
</head>
<body>
 <div class="row" id="SearchList">
<!--       <div class="col-md-4">
          <div class="form-group">
              <label class="col-sm-3 control-label">试卷类型</label>
              <div class="col-sm-6">
                  <select id="examinationType" class="form-control">
                      <option value="0">--请选择--</option>
                      <option value="1">正式</option>
                      <option value="2">模拟</option>
                  </select>
              </div>
          </div>
      </div> -->
      <div class="col-md-6">
          <div class="form-group">
              <label class="col-sm-2 control-label">用户姓名:</label>
              <div class="col-sm-4">
                  <input id="userName" class="form-control" />
              </div>
          </div>
      </div>
      <div class="box-footer">
      	<input class="btn" type="button" id="screeningBtn" name="screeningBtn" value="筛选"  onclick="screeningSearch();" />

                        </div>
  </div>
<div style="width:101%;height:100%" class="box-body">
    <table id="tb_user" class="table-no-bordered"></table>
</div>
</body>
<script type="text/javascript">
$(function () {
	$("#tb_user").bootstrapTable({
        url: "../user/userList.do",         //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
        //toolbar: toolbar,                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 20,                       //每页的记录行数（*）
        pageList: [20],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列
        showRefresh: false,                  //是否显示刷新按钮
        //minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "Id",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表onEditableSave
        //sortOrder: "asc",                   //排序方式
        queryParams: function (params) {
            var user = {   //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
                currPage : params.offset / params.limit + 1,
                pageSize : params.limit,
                realName:$("#userName").val()
           };
           return user;
        },//传递参数（*）  
        columns: [
        	{
                checkbox: true
            },
        	{
                field: 'NAME',
                align: 'center',
                title: '登录名'
            },
            {
                field: 'BIRTHDAY',
                align: 'center',
                title: '生日',
                formatter:function(value,rowData,rowIndex){
                	var JsonDateValue = new Date(value.time);
                	var text = JsonDateValue.toLocaleString(); 
                	return text;
                }
            },
            {
                field: 'CREATEDATE',
                align: 'center',
                title: '创建时间',
                formatter:function(value,rowData,rowIndex){
                	var JsonDateValue = new Date(value.time);
                	var text = JsonDateValue.toLocaleString(); 
                	return text;
                }
            },
            {
                field: 'ACCOUNT',
                align: 'center',
                title: '账号'
            }, 
            {
                field: 'operate',
                align: 'center',
                title: '操作'
            }
        ],
        onPageChange: function (number, size) {

        },
        onClickRow: function (row, element, field) {
			
        },
        onDblClickRow: function (row, obj) {

        }
    }); 
	


});

function screeningSearch() {
    $("#tb_user").bootstrapTable('refresh');
    //$("#tb_ExaminationInterval").bootstrapTable('refresh', { url: "/Admin/ExaminationInterval/SearchLists", query: { "examinationType": $("#examinationType").val(), "examinationTitle": $("#examinationTitle").val() } });
}
</script>
</html>
