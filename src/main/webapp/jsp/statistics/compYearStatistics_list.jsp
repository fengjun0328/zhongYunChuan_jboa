<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%--<%@taglib uri="/struts-tags"  prefix="s"%>--%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/statics/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function confirm(){
		if(isNaN(document.queryForm.startYear.value)){
			alert("输入的开始年份不合法！");
			return false;
		}
		if(isNaN(document.queryForm.endYear.value)){
			alert("输入的结束年份不合法！");
			return false;
		}
		return true;
	}
	
	function submitSearch(){
		if(confirm()){
			document.queryForm.submit();
		}
	}
</script>
<div class="action  divaction">
	<div class="t">年度统计列表</div>
	<div class="pages">
	     <form action="/compYearStatistics/getList" name="queryForm">
	       		<label for="time">开始年份</label>
	       		<textarea name="startYear" id="startYear" class="nwinput">${startYear}</textarea>
	       		<label for="end-time">结束年份</label>
	       		<textarea name="endYear" id="endYear" class="nwinput">${endYear}</textarea>
		        <%-- <s:submit cssClass="submit_01" value="查询"/> --%>
		        <input type="button" value="提交" class="submit_01"
								onclick="submitSearch()"/>
	     </form>
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>总计</td>
	        <td>年份</td>
	       <!--  <td>月份</td> -->
	        <!-- <td>部门</td> -->
	        <td>操作</td>
	      </tr>
	      <c:forEach items="${compYearList}" var="claimVoucherStatistics" begin="0" varStatus="s">
	      <tr>
	        <td><s:property value="#"/>${claimVoucherStatistics.id}</td>
	        <td>￥<s:property value="#"/>${claimVoucherStatistics.totalCount}</td>
	        <td><s:property value="#"/>${claimVoucherStatistics.year}年</td>
	        <%-- <td><s:property value="#claimVoucherStatistics.month"/>月</td> --%>
	        <%-- <td><s:property value="#claimVoucherStatistics.department.name"/></td> --%>
	        <td>
	        <%-- <a href="claimVoucher_getClaimVoucherById.action?claimVoucher.id=<s:property value="#claimVoucher.id"/>">
	        	<img src="${ images}/search.gif" width="16" height="15" />
	        </a> --%>
	        <a href="/compYearStatistics/getDetail?currYear=${claimVoucherStatistics.year}">
	        <!-- <a href="claimVoucherStatistics_getDeptVoucherDetailByMonth.action?year=2013&selectMonth=7&departmentId=2"> -->
	        <img src="${images}/search.gif" width="16" height="15" />
	        </a>
	        </td>
	      </tr>
	      </c:forEach>
	       <tr>
	        <%--<td colspan="6" align="center">
		      	<c:import url="../claim/rollPage.jsp" charEncoding="UTF-8">
				<c:param name="formName" value="document.forms[0]"/>
				<c:param name="totalRecordCount" value="${pageSupport.totalCount}"/>
				<c:param name="totalPageCount" value="${pageSupport.totalPageCount}"/>
				<c:param name="currentPageNo" value="${pageSupport.currPageNo}"/>
  			</c:import>--%>
  			</td>
	      </tr>
	    </table>        
       </div>
      </div>