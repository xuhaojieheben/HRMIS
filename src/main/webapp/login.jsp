<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HRMIS</title>
<link href="static/login/login.css" rel="stylesheet" media="all" />
<script type="text/javascript" src="static/js/jquery-2.2.3.min.js"></script>

</head>
<body>
	<div class="bg">
	   <div class="wel">HRMIS</div>
       <div class="user">
      	   <div id="yonghu" style="">账号</div>
      	   <input type="text" id="account" name="account" value="" />
       </div>
       <div class="password">
       	   <div id="yonghu" >密码</div>
      	   <input class="" type="password" id="password" name="password" value="" />
       </div>
       <div class="rem">
      	  <input type="checkbox" name="rember" id="rember" value="" />
       	  <div id="reb">
       	 	 记住密码
       	  </div>
       </div>
       <input class="btn" type="button" id="login" name="login" value="登录"  onclick="doL();" />
	</div>
</body>
<script type="text/javascript">
$(function () {});
function doL() {
	class UserParam {
		constructor(account, password) {
			this.account = account;
			this.password = password;
		}
	};
	var userinfo = new UserParam($("#account").val(), $("#password").val());
	var jsonString = JSON.stringify(userinfo);
	$.ajax({  
        url : "login/doLogin.do",  
        type: "POST", 
        dataType: "json",
        contentType: "application/json",
        data:jsonString, 
        success : function(data) {
        	if(data.state == 0){
        		location.href="index/index.do";
        	}else{alert(data.data)}
        },  
        error : function(data) {  
            console.log(data);
        }  
    });
}
</script>
</html>
