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
   	function delVoucher(id){
   		if(!confirm('确定删除报单吗')) return;
   		
   		document.claimVoucherForm.action = "/claimVoucher/deleteClaimVoucherById/"+id;
   		document.claimVoucherForm.submit();
   		
   	}
   	
</script>
<div class="action  divaction">
	<div class="t">报销单列表</div>
	<div class="pages">
		<div class="forms">
			 <form action="/claimVoucher/searchClaimVoucher" name="queryForm">
	       		<label>报销单状态</label>
                 <select name="status">
                     <option value="">全部</option>
                     <c:forEach items="${statusMap}" var="item">
                         <option value="${item.key}"  <c:if test="${status == item.key}">selected="selected"</c:if> >${item.value}</option>
                     </c:forEach>
                 </select>
		       <label for="time">开始时间</label>
		       <input type="text" name="startDate" id="startDate" class="nwinput" value="${startDate}"/>
		       <label for="end-time">结束时间</label>
               <input type="text"  name="endDate" id="endDate" class="nwinput" value="${endDate}"/>
		       <input type="hidden" name="pageNo" value="1"/>
		 	   <input type="hidden" name="pageSize" value="5"/>
               <input type="submit" value="查询" class="submit_01"/>
	       </form>
	     </div>
	<!--增加报销单 区域 开始-->
	<form action="/claimVoucher/searchClaimVoucher" name="claimVoucherForm">
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>填报日期</td>
	        <td>填报人</td>
	        <td>总金额</td>
	        <td>状态</td>
	        <td>待处理人</td>
	        <td>操作</td>
	      </tr>
          <c:forEach items="${pageSupport.items}" var="claimVoucher">
              <td><a href="/claimVoucher/getClaimVoucherById/${claimVoucher.id}">${claimVoucher.id}</a></td>
              <td><fmt:formatDate value="${claimVoucher.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/> </td>
              <td>${claimVoucher.creator.name}</td>
              <td>${claimVoucher.totalAccount}</td>
              <td>${claimVoucher.status}</td>
              <td>${claimVoucher.nextDeal.name}</td>
              <td>
                  <c:if test="${claimVoucher.status == '新创建' || claimVoucher.status == '已打回'}">
                      <a href="/claimVoucher/toUpdate/${claimVoucher.id}">
                          <img src="${images}/edit.gif" width="16" height="16" />
                      </a>
                      <a onClick="delVoucher(${claimVoucher.id})" href="#">
                          <img src="${images}/delete.gif" width="16" height="16" />
                      </a>
                  </c:if>
                  <img src="${images}/save.gif" width="16" height="16" />
                  <a href="/claimVoucher/getClaimVoucherById/${claimVoucher.id}">
                      <img src="${images}/search.gif" width="16" height="15" />
                  </a>
                  <c:if test="${claimVoucher.nextDeal.name == sessionScope.employee.name}">
                      <c:if test="${claimVoucher.status == '已提交' || claimVoucher.status == '侍审批' || claimVoucher.status == '已审批'}">
                          <a href="/claimVoucher/toCheck/${claimVoucher.id}">
                              <img src="${images}/sub.gif" width="16" height="16" />
                          </a>
                      </c:if>
                  </c:if>
              </td>
              </tr>
          </c:forEach>
	      <tr>
	        <td colspan="6" align="center">
		      	<c:import url="rollPage.jsp" charEncoding="UTF-8">
				<c:param name="formName" value="document.forms[0]"/>
				<c:param name="totalRecordCount" value="${pageSupport.totalCount}"/>
				<c:param name="totalPageCount" value="${pageSupport.totalPageCount}"/>
				<c:param name="currentPageNo" value="${pageSupport.currPageNo}"/>
  			</c:import> 
  		  	</td>
  		  </tr>
	    </table>      
	   </form>
	          <!--增加报销单 区域 结束-->
       </div>
      </div>