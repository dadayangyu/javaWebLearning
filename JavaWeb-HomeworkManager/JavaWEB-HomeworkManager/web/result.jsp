<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2020/3/9
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%--显示添加作业、学生、提交作业结果--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作结果</title>
</head>
<body>
<%
    String type = (String)request.getAttribute("type");
    boolean isOK = (boolean)request.getAttribute("isOK");
    if (type.equals("addHomework")){

        if (isOK){
%>

<p style="margin: 50px; font-family: Arial; font-size: 50px; color: darkorange"><%="添加作业成功!"%></p>

<%
}else{
%>

<p style="margin: 50px; font-family: Arial; font-size: 50px;color: red"><%="添加作业失败!!!"%></p>

<%
    }
}else if (type.equals("addStudent")){
    if (isOK){
%>

<p style="margin: 50px; font-family: Arial; font-size: 50px; color: darkorange"><%="添加学生成功!"%></p>

<%
}else{
%>

<p style="margin: 50px; font-family: Arial; font-size: 50px;color: red"><%="已有学生信息!无法添加！"%></p>

<%
    }
}else if (type.equals("addStudentHomework")){
    if (isOK){
%>

<p style="margin: 50px; font-family: Arial; font-size: 50px; color: darkorange"><%="作业提交成功!"%></p>

<%
}else {
%>

<p style="margin: 50px; font-family: Arial; font-size: 50px; color: darkorange"><%="作业提交失败!"%></p>

<%
        }
    }
%>
<a href="index.jsp" style="margin: 50px">返回首页</a>
</body>
</html>