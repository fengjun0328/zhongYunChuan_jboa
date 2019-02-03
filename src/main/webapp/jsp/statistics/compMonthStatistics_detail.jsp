<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%--<%@taglib uri="/struts-tags"  prefix="s"%>--%>
<%
    String path = request.getContextPath();
%>
<%@ include file="../common/taglib.jsp" %>
<link href="<%=request.getContextPath() %>/statics/css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/common.js"></script>
<script type="text/javascript">
    $(function () {
        $("#submit_01").submit(function () {
            var year = $("#year").val();
            var currMonth = $("#currMonth").val();
            $.ajax({
                type: "POST",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                url: "/compMonthStatistics/getDetailExcel",
                data: JSON.stringify({"year":year,"currMonth":currMonth}),
                success: function (data) {
                    alert(data)
                }
            });
            return false;
        });
    })
</script>

<div class="action  divaction">
    <div class="t">月度统计详情</div>
    <div class="pages">
        <form action="/compMonthStatistics/getDetailExcel" name="queryForm">
            <label for="time">年份:</label>
            ${year}
            <label for="end-time">月份:</label>
            ${currMonth}
            <input type="hidden" name="year" value="${year}"/>
            <input type="hidden" name="currMonth" value="${currMonth}"/>
            <input type="submit" class="submit_01" value="导出报表"/>
        </form>


        <table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
            <tr class="even">
                <td>部门编号</td>
                <td>部门</td>
                <td>报销总额</td>
                <td>年份</td>
                <td>月份</td>
            </tr>
            <c:forEach items="${compMonthDetail}" var="deptClaimVoucher" begin="0" varStatus="s">
                <tr>
                    <td>${deptClaimVoucher.creator.department.id}</td>
                    <td>${deptClaimVoucher.creator.department.name}</td>
                    <td>￥${deptClaimVoucher.totalAccount}</td>
                    <td><fmt:formatDate value="${deptClaimVoucher.createTime}" pattern="yyyy"/> 年</td>
                    <td><fmt:formatDate value="${deptClaimVoucher.createTime}" pattern="MM"/> 月</td>

                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td bgcolor="yellow">总计</td>
                <td bgcolor="yellow">￥${totalCount}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>


        </table>
    </div>
    <span style="display:none;"><iframe id="downloadIframe" src="" style="width:0px;height:0px;"></iframe></span>
    <!--增加报销单 区域 结束-->
</div>
<div style="">
    <center><img
            src="<%=path%>/jsp/statistics/compMonStatistics_getDetailChart.action?year=${year}&currMonth=${currMonth}">
    </center>

</div>

</div>