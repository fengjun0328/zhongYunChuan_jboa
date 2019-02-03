<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%--<%@taglib uri="/struts-tags"  prefix="s"%>--%>
<%
    String path = request.getContextPath();
%>
<%@ include file="../common/taglib.jsp" %>
<link href="<%=request.getContextPath() %>/statics/css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/common.js"></script>
<script type="text/javascript">
    /* $(function(){
        $('#chart_img').attr('src','claimVoucherStatistics_createDetailChart.action?year=2013&month=7');
    }); */
    <%-- year = <%=request.getAttribute("year")%>;
    selectMonth = <%=request.getAttribute("selectMonth")%>;
    action = "claimVoucherStatistics_createDetailChart.action?year="+year+"&selectMonth="+selectMonth;
    document.getElementById("chart_img").setAttribute("src", action); --%>
    $(function () {
        $("#submit_01").submit(function () {
            var year = $("#year").val();
            var selectMonth = $("#selectMonth").val();
            var departmentId = $("#departmentId").val();
            $.ajax({
                type: "POST",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                url: "/deptMonthStatistics/createDetailExcel",
                data: JSON.stringify({"year":year,"selectMonth":selectMonth,"departmentId":departmentId}),
                success: function (data) {
                    alert(data)
                }
            });
            return false;
        });
    })
</script>

<div class="action divaction">
    <div class="t">月度统计详情</div>
    <div class="pages">
        <form action="/deptMonthStatistics/createDetailExcel" name="queryForm">
            <label for="time">年份:</label>
            ${year}
            <label for="end-time">月份:</label>
            ${selectMonth}
            <input type="hidden" name="year" id="year" value="${year}"/>
            <input type="hidden" name="selectMonth" id="selectMonth" value="${selectMonth}"/>
            <input type="hidden" name="departmentId" id="departmentId" value="${departmentId}"/>
            <input type="submit" id="submit_01" class="submit_01" value="导出报表"/>
        </form>


        <table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
            <tr class="even">
                <td>编号</td>
                <td>报销人</td>
                <td>报销总额</td>
                <td>年份</td>
                <td>月份</td>
                <td>部门</td>
            </tr>
            <c:forEach items="${statisticsDetailOfDeptMonth}" var="claimVoucher" begin="0" varStatus="s">
                <tr>
                    <td>${claimVoucher.creator.sn}</td>
                    <td>${claimVoucher.creator.name}</td>
                    <td>￥${claimVoucher.totalAccount}</td>
                    <td>${year}年</td>
                    <td>${selectMonth}月</td>
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
<div style="">
    <center><img
            src="<%=path%>/claimVoucherStatistics/createDetailChart?year=${year}&amp;selectMonth=${selectMonth}&amp;departmentId=${departmentId}"/>
    </center>
</div>