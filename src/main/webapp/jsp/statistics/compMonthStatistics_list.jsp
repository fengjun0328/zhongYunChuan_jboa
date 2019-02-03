<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%--<%@taglib uri="/struts-tags"  prefix="s"%>--%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/statics/css/style.css" rel="stylesheet" type="text/css" />
<div class="action  divaction">
	<div class="t">月度统计列表</div>
	<div class="pages">
	     <form action="/compMonthStatistics/getList" name="queryForm">
	     		<label for="time">年份</label>
	     		<select name="year">
					<c:forEach var="year" step="1" begin="2013" end="2019">
						<option value="${year}" <c:if test="${year==currYear}">selected="selected"</c:if>>${currYear}</option>
					</c:forEach>
				</select>
	       		<label for="time">开始月份</label>
	       		<select name="startMonth">
					<c:forEach var="month" step="1" begin="1" end="12">
						<option value="${month}" <c:if test="${startMonth==month}">selected="selected"</c:if>>${month}</option>
					</c:forEach>
				</select>
	       		<label for="end-time">结束月份</label>
	       		<select name="endMonth">
					<c:forEach var="month" step="1" begin="1" end="12">
						<option value="${month}" <c:if test="${endMonth==month}">selected="selected"</c:if>>${month}</option>
					</c:forEach>
				</select>
		 	    <!-- <input type="hidden" name="year" value="2013"/> -->
		        <input type="submit" class="submit_01" value="查询"/>
	     </form>
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>总计</td>
	        <td>年份</td>
	        <td>月份</td>
	        <!-- <td>部门</td> -->
	        <td>操作</td>
	      </tr>
	      <c:forEach items="${compMonthList}" var="claimVoucherStatistics" begin="0" varStatus="s">
	      <tr>
	        <td>${claimVoucherStatistics.id}</td>
	        <td>￥${claimVoucherStatistics.totalCount}</td>
	        <td>${claimVoucherStatistics.year}年</td>
	        <td>${claimVoucherStatistics.month}月</td>
	        <%-- <td><s:property value="#claimVoucherStatistics.department.name"/></td> --%>
	        <td>
	        <%-- <a href="claimVoucher_getClaimVoucherById.action?claimVoucher.id=<s:property value="#claimVoucher.id"/>">
	        	<img src="${ images}/search.gif" width="16" height="15" />
	        </a> --%>
	        <a href="/compMonthStatistics/getDetail?year=${claimVoucherStatistics.year}&currMonth=${claimVoucherStatistics.month}">
	        <!-- <a href="claimVoucherStatistics_getDeptVoucherDetailByMonth.action?year=2013&selectMonth=7&departmentId=2"> -->
	        <img src="${images}/search.gif" width="16" height="15" />
	        </a>
	        </td>
	      </tr>
	      </c:forEach>
	       <tr>
	       <%-- <td colspan="6" align="center">
		      	<c:import url="../claim/rollPage.jsp" charEncoding="UTF-8">
				<c:param name="formName" value="document.forms[0]"/>
				<c:param name="totalRecordCount" value="${pageSupport.totalCount}"/>
				<c:param name="totalPageCount" value="${pageSupport.totalPageCount}"/>
				<c:param name="currentPageNo" value="${pageSupport.currPageNo}"/>
  			</c:import>
  			</td>--%>
	      </tr>
	    </table>        
       </div>
      </div>