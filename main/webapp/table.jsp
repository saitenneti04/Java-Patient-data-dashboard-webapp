<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Data Table</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h2>Data Table</h2>
<%
    List<Map<String, String>> data = (List<Map<String, String>>) request.getAttribute("data");
    List<String> columnNames = (List<String>) request.getAttribute("columnNames");
    if (data != null && !data.isEmpty() && columnNames != null) {
%>
<table border="1">
    <thead>
    <tr>
        <% for (String columnName : columnNames) { %>
        <th><%= columnName %></th>
        <% } %>
    </tr>
    </thead>
    <tbody>
    <% for (Map<String, String> row : data) { %>
    <tr>
        <% for (String columnName : columnNames) { %>
        <td><%= row.get(columnName) %></td>
        <% } %>
    </tr>
    <% } %>
    </tbody>
</table>
<% } else { %>
<p>No data available.</p>
<% } %>
</body>
<jsp:include page="/footer.jsp"/>
</html>
