<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/statics/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/My97DatePicker/WdatePicker.js"></script>
<script>
	$(function(){
			 //日期选择控件
		 	$("#startDate").click(function(){
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true, readOnly:true });
			});
			$("#endDate").click(function(){
				WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true, readOnly:true });
			});
		});
</script>
<div class="action  divaction">
	<div class="t">请假列表</div>
	<div class="pages">
		<div class="forms">
		<form action="/leave/searchLeave" name="queryForm">
	       <label for="time">开始时间</label>
	       <textarea name="startDate" id="startDate" class="nwinput">${startDate}</textarea>
	       <label for="end-time">结束时间</label>
	       <textarea name="endDate" id="endDate" class="nwinput">${endDate}</textarea>
	       <input type="hidden" name="pageNo" value="1"/>
		   <input type="hidden" name="pageSize" value="5"/>
		   <input type="submit" class="submit_01" value="查询"/>
	     </form>
	     </div>
	<!--增加报销单 区域 开始-->
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>名称</td>
	        <td>发起时间</td>
	        <td>审批时间</td>
	        <td>审批意见</td>
	        <td>审批状态</td>
	        <td>操作</td>
	      </tr>
	      <c:forEach items="${pageSupport.items}" var="leave" begin="0" varStatus="s">
	      <tr>
	        <td><a href="/leave/getLeaveById/${leave.id}">${leave.id}</a></td>
	        <td>${leave.creator.name}请假${leave.leaveDay}天</td>
	        <td><fmt:formatDate value="${leave.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
	        <td><fmt:formatDate value="${leave.modifyTime}" pattern="yyyy-MM-dd HH:mm"/></td>
	        <td>${leave.approveOpinion}</td>
	        <td>${leave.status}</td>
	        <td>
	       	 <a href="/leave/getLeaveById/${leave.id}"><img src="${images}/search.gif" width="16" height="15" /></a>
	       	  <c:if test="${leave.nextDeal.name == sessionScope.employee.name}">
		        <c:if test="${leave.status == '待审批'}">
	       	 		<a href="/leave/toCheck/${leave.id}">
	       	 		<img src="${images}/sub.gif" width="16" height="16" /></a> 
	       	 	</c:if>
	       	 </c:if>
	        </td>
	      </tr>
	      </c:forEach>
	      <tr>
	        <td colspan="7" align="center">
		      	<c:import url="../common/rollPage.jsp" charEncoding="UTF-8">
				<c:param name="formName" value="document.forms[0]"/>
				<c:param name="totalRecordCount" value="${pageSupport.totalCount}"/>
				<c:param name="totalPageCount" value="${pageSupport.totalPageCount}"/>
				<c:param name="currentPageNo" value="${pageSupport.currPageNo}"/>
  			</c:import> 
  		  	</td>
  		  </tr>
	    </table>        
	          <!--请假 区域 结束-->
       </div>
      </div>