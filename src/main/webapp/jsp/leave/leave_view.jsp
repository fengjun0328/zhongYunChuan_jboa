<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>北大青鸟办公自动化管理系统</title>
		<link href="<%=request.getContextPath() %>/statics/css/style.css" rel="stylesheet" type="text/css" />
	</head>
<body>
<div class="action  divaction">
			<div class="t">
				查看请假
			</div>
			<div class="pages">
				<!--增加报销单 区域 开始-->
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="addform-base">
					<caption>
						基本信息
					</caption>
					<tr>
						<td width="36%">
							姓名：${leave.creator.name}
						</td>
						<td width="64%">
							部门：${leave.creator.department.name}
						</td>
					</tr>
					<tr>
						<td>
							开始时间：<fmt:formatDate value="${leave.startTime}" pattern="yyyy-MM-dd hh:mm"/>
						</td>
						<td>
							结束时间：<fmt:formatDate value="${leave.endTime}" pattern="yyyy-MM-dd hh:mm"/>
						</td>
					</tr>
					<tr>
						<td>
							请假天数：${leave.leaveDay}
						</td>
						<td>
							休假类型：${leave.leaveType}
						</td>
					</tr>
					<tr>
						<td>
							请假事由：${leave.reason}
						</td>
						<td>
							审批状态：${leave.status}
						</td>
					</tr>
				</table>
				<p>
					-------------------------------------------------------------------------------
				</p>
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="addform-base">
					<tr>
						<td>
							审批意见：
						</td>
					</tr>
					<tr>
						<td>
							<textarea name="approveOpinion" id="textarea" cols="45" rows="5">${leave.approveOpinion}</textarea>
						</td>
					</tr>
					<!--表单提交行-->
					<tr>
						<td colspan="4" class="submit">
							<input type="button" value="返回" onclick="window.history.go(-1)" class="submit_01"/>
						</td>
					</tr>
	
				</table>
				<!--增加报销单 区域 结束-->
			</div>
		</div>
	</body>
</html>