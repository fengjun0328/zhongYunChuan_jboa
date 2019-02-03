<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%--<%@taglib uri="/struts-tags"  prefix="s"%>--%>
<%
String path = request.getContextPath();
%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/statics/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/common.js"></script>
<script type="text/javascript">
	$(function () {
		$("#submit_01").submit(function () {
		    var currYear=$("#currYear").val();
		    var departmentId=$("#departmentId").val();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                dataType: "json",
                url: "/deptYearStatistics/createDetailExcel/",
                data: JSON.stringify({"currYear":currYear,"departmentId":departmentId}),
                success: function (data) {
                    alert(data);
                }
            })
			return false;
        });
    })
</script>
<div class="action  divaction">
	<div class="t">年度统计详情</div>
	<div class="pages">
	     <form action="/deptYearStatistics/createDetailExcel" name="queryForm">
	       		<label for="time">年份:</label>
	       		${currYear}
	       		<input type="hidden" name="currYear" value="${year}" id="currYear"/>
	       		<input type="hidden" name="departmentId" value="${departmentId}" id="departmentId"/>
		        <input type="submit" id="submit_01" class="submit_01" value="导出报表"/>
	     </form>
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>报销人</td>
	        <td>报销总额</td>
	        <td>年份</td>
	        <td>部门</td>
	      </tr>
	      <c:forEach items="${deptDetailList}" var="claimVoucher" begin="0" varStatus="s">
	      <tr>
	        <td>${claimVoucher.creator.sn}</td>
	        <td>${claimVoucher.creator.name}</td>
	        <td>￥${claimVoucher.totalAccount}</td>
	        <td>${year}年</td>
	        <td>${claimVoucher.creator.department.name}</td>
	      </tr>
	      </c:forEach>
	      <tr>
	      	<td></td>
	      	<td bgcolor="yellow">总计</td>
	      	<td bgcolor="yellow">￥${detailCount}</td>
	      	<td></td>
	      	<td></td>
	      	<td></td>
	      </tr>
	    </table>        
       </div>
       <span style="display:none;"><iframe id="downloadIframe" src="" style="width:0px;height:0px;"></iframe></span>
	          <!--增加报销单 区域 结束-->
       </div>
       <div style=""><div align="left"> 
       	  <center><img src="<%=path%>/deptYearStatistics/createDetailChart?currYear=${currYear}&departmentId=${departmentId}"></center></div>
       <%-- <img src="<%=path%>/jsp/statistics/claimVoucherStatistics_createDetailChart.action?year=<%=request.getAttribute("year")%>&amp;selectMonth=<%=request.getAttribute("selectMonth")%>&amp;departmentId=<%=request.getAttribute("departmentId")%>"> --%>
       </div>
       
      </div>