<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="taglib.jsp"%>
<div class="nav" id="nav">
	<div class="t"></div>
   		<dl class="open">
	       	<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">报销单管理</dt>
	           <dd><a href="/claimVoucher/searchClaimVoucher">查看报销单</a></dd>
	           <c:if test='${sessionScope.employee_position=="员工"}'>
	           <dd><a href="/claimVoucher/toAdd">添加报销单</a></dd>
	           </c:if>
		</dl>
       <dl>
       		<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">请假管理</dt>
	           <dd><a href="/leave/searchLeave">查看请假</a></dd>
	           <c:if test='${sessionScope.employee_position=="员工"}'>
		       <dd><a href="/leave/toEdit">申请请假</a></dd>
		       </c:if>
       </dl>
       <c:if test='${sessionScope.employee_position=="部门经理"}'>
       <dl>
       		<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">统计报表</dt>
		       <dd><a href="/deptMonthStatistics/getDeptStatisticsByMonth">报销单月度统计</a></dd>
		       <dd><a href="/deptYearStatistics/findDeptYearStatisticsList">报销单年度统计</a></dd>
       </dl>
       </c:if>
       <c:if test='${sessionScope.employee_position=="总经理" || sessionScope.employee_position=="财务"}'>
       <dl>
       		<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">统计报表</dt>
		       <dd><a href="/compMonthStatistics/getList">报销单月度统计</a></dd>
		       <dd><a href="/compYearStatistics/getList">报销单年度统计</a></dd>
       </dl>
      </c:if>
       <dl>
       	<dt onclick="this.parentNode.className=this.parentNode.className=='open'?'':'open';">信息中心</dt>
           <dd>信心收件箱</dd>
           <dd>信心发件箱</dd>
       </dl>
</div>
