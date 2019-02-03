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
		<script type="text/javascript">
			function checkClaimVoucher(checkResult){
		   		if(!confirm('确定'+checkResult+'报单吗')) return;
		   		document.checkResultForm.result.value = checkResult;
		   		document.checkResultForm.submit();
		   		
		   	}
		</script>
	</head>
<body>
    <div class="action  divaction">
    	<div class="t">审核报销单</div>
   		<div class="pages">
        	<!--增加报销单 区域 开始-->
                <table width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
                  <caption>基本信息</caption>
                
                  <tr>
                      <td >编&nbsp;&nbsp;号：${claimVoucher.id}</td>
                      <td>填&nbsp;写&nbsp;人：${claimVoucher.creator.name}</td>
                      <td>部&nbsp;&nbsp;门：${claimVoucher.creator.department.name}</td>
                      <td>职&nbsp;&nbsp;&nbsp;&nbsp;位：${claimVoucher.creator.position.nameCn}</td>
                  </tr>
                  <tr>
                      <td>总金额：<input type="text" name="totalAccount" id="totalAccount" readonly="true" value="${claimVoucher.totalAccount }"/></td>
                      <td>填报时间：<fmt:formatDate value="${claimVoucher.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                      <td>状态：${claimVoucher.status}</td>
                      <td>待处理人：${claimVoucher.nextDeal.name}</td>
                  </tr>
                  <tr>
                  	<td colspan="4"><p>-------------------------------------------------------------------------------</p></td>
                  </tr>
                </table>
          <p>&nbsp;</p>
                <table width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
                  <tr>
                    <td>项目类别</td>
                    <td>项目金额</td>
                    <td>费用说明</td>
                  </tr>
                <c:forEach items="${claimVoucher.detailList}" var="claimDetail" begin="0" varStatus="s">
				<tr>
					<td>${claimDetail.item}</td>
					<td>￥${claimDetail.account}</td>
					<td>${claimDetail.desc}</td>
				</tr>
				</c:forEach>
      </table>
      <p>&nbsp;</p>
      <p>-------------------------------------------------------------------------------</p>
      
      <table width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
      	<c:forEach items="${claimVoucher.checkResultList}" var="checkResult" begin="0" varStatus="s">
         <tr>
           <td width="27%"><s:property value="" />${checkResult.checkEmployee.name}(${checkResult.checkEmployee.position.nameCn}<s:property value="" />)</td>
           <td width="20%">&lt;<fmt:formatDate value="${checkResult.checkTime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
           <td width="38%">审核：<span class="red"><strong><s:property value="" />${checkResult.result}</strong></span></td>
         </tr>
         <tr>
             <td>审核意见：<strong><s:property value="" />${checkResult.comment}</strong></td>
           <td>&nbsp;</td>
           <td>&nbsp;</td>
         </tr>
         <tr>
         	<td colspan="3">
         		<p>-------------------------------------------------------------------------------</p>
           	</td>
         </tr>
         </c:forEach>
    </table>
    <form action="/checkResult/checkClaimVoucher" name="checkResultForm">
    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="addform-base">
		<tr>
			<td>
				审批意见：
			</td>
		</tr>
		<tr>
			<td>
				<textarea name="comment" id="textarea" cols="45"
					rows="5"></textarea>
			</td>
		</tr>
		 <!--表单提交行-->
		 <tr>
			<td colspan="3" class="submit">
				<input type="button" name="button" id="button" value="审批通过"
					class="submit_01" onclick="checkClaimVoucher('通过')" />
				<input type="hidden" id="claimId" name="claimVoucher.id" value="${claimVoucher.id}"/>
				<input type="hidden" id="result" name="result" value=""/>
				<c:if test='${sessionScope.employee_position != "财务"}'>
					<input type="button" name="button" id="button" value="审批拒绝"
						class="submit_01" onclick="checkClaimVoucher('拒绝')" />
					<input type="button" name="button" id="button" value="打回"
						class="submit_01" onclick="checkClaimVoucher('打回')" />
				</c:if>
			</td>
		 </tr>
	</table>
	</form>
    <p>&nbsp;</p>
   
    <p>&nbsp;</p>
    <p><input type="button" value="返回" onclick="window.history.go(-1)" class="submit_01"/></p>       
<!--增加报销单 区域 结束-->
        </div>
    </div>
    
</body>
</html>
