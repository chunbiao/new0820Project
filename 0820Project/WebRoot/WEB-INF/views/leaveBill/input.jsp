<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/js/commons.jspf" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议管理</title>
<link rel="stylesheet" href="${basePath}/css/input.css" type="text/css"></link>
<script src="${basePath}/js/input.js" type="text/javascript"> </script> 
</head>
<body>
	<form action="leaveBillAction_save.action" method="POST">
			<fieldset>
				<legend>
					填写申请内容
				</legend>
				<p>
					<label for="theme">会议主题：<input id="Mtheme" name="Mtheme" size=50></input></label>
				</p>
				<p>
					<label for="lastingTime">会议时长：<input id="days" name="days" size=5></input>小时</label>
				</p>
					<p>
						<label for="address">会议地点：<input id="address" name="address" size=30></input></label>
					</p>
				<p>
					<label for="meetingDate">会议时间：<input type="datetime" name="meetingDate" value="yyyy-MM-dd hh:mm"></input></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>  
				
				<p id="participantsList">
					<label>参会人员：</label>
				</p>  
				<ul id="listFrame">
					<li class="listTitle"><label id="mainTitle">部门</label>
						<ul class="apartment">
							<li class="listTitle"><label id="supplierLabel">后勤部</label>
								<ul class="supplierList">
									<li><input type="checkbox" name="canteen"></input><label>食堂</label></li>
									<li><input type="checkbox"></input><label>锅炉房</label></li>
									<s:if test="#supplier!=null&&#supplier.size()>0">
										<s:iterator value="#supplier">
											<li><input type="checkbox" name="<s:property value='user.id'/>"><label><s:property value="user.name"/></label></input></li>
										</s:iterator>
									</s:if>
								</ul>
							</li>
							<li class="listTitle"><label id="collegeLabel">学院</label>
								<ul class="collegeList">
									<li class="listTitle" id="SWCollege"><input type="checkbox"></input><label>软微学院</label>
										<ul class="numbersList">
											<li class="listTitle"><input type="checkbox"></input><label>教师</label>
												<ul class="teacherList">
													<li><input type="checkbox" name="刘志强"></input><label>刘志强</label></li>
													<li><input type="checkbox" name="周玲"></input><label>周玲</label></li>
													<li><input type="checkbox"></input><label>聂烜</label></li>
												</ul>
											</li>
											<li class="listTitle"><input type="checkbox"></input><label>学生</label>
												<ul class="studentList">
													<li><input type="checkbox"></input><label>宋春彪</label></li>
													<li><input type="checkbox"></input><label>赵耀</label></li>
													<li><input type="checkbox"></input><label>孙为强</label></li>
													<li><input type="checkbox"></input><label>赵乙</label></li>
												</ul>
											</li>
										</ul>
									</li>
									<li class="listTitle"><input type="checkbox"></input><label>计算机学院</label></li>
									<li class="listTitle"><input type="checkbox"></input><label>管理学院</label></li>
														</ul>
													</li>
												</ul>
											</li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
				
			</fieldset>
			<input type="submit" value="提交" id="submitId"></input>
		</form>  
		
		<script>
			var submitButt = window.document.getElementById("submitId");
			submitButt.onclick = function(){
				var dateInput = window.document.getElementByTagName("INPUT")[3];
				alert(dateInput.value);
			}
		</script>  
		
</body>
</html>