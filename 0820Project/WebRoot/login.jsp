<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<title>登录页面</title>
	<%@ include file="/js/commons.jspf" %>
	<link href="${basePath}/css/logIn.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		if(parent != window){
			parent.location.href = window.location.href;
		}
		function func_login(){
			document.forms[0].submit();
		}
	</script>
</head>
	<body>
		<div class="content">
			<form action="loginAction_login.action" method="post" class="logInArea">
				<h1>登陆</h1>
				<hr>
				<div id="inputArea">
					<div id="left">
						<p><em>提示：</em></p>
						<p>* 教师用户的用户名为教师工号，学生用户的用户名为学生的学号。</p>
						<p>* 用户的初始密码为000000，请第一次登陆后务必修改密码，防止您的信息泄露。</p>
					</div>
					<div id="right">
						<p><label class="inputLabel">用户名：<input type="input" name = "name"></input></label></p>
						<!--  <p><label class="inputLabel">密码：<input type="password"></label></p> -->
						<p>
							<input type="submit" value="登陆"></input>
						<a>
							忘记密码？
						</a>
						</p>
					</div>
				</div>
			</form>
		</div> 
	</body>
</html>