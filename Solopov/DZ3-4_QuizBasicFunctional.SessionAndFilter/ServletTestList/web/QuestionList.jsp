<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Username
  Date: 19.04.2018
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz List</title>
</head>
<body>
<table>

    <c:forEach items="${list}" var="question">
        <tr>
            <td> ${question.getId()}.</td>
            <td><b> ${question.getQuestion()} </b></td>
            <td><a href="delete?id=${question.getId()}" content=" ">Delete</a></td>
        </tr>

        <td><c:forEach items="${question.answer}" var="answer">
            <p><input name=rgroup${question.getId()} type="radio" value=value${question.getId()}> ${answer}</p>
        </c:forEach></td>
    </c:forEach>
</table>
<p>
<form name = "formAddQuestion" method="post" action="quizlist">
    <input type="submit" name="addQuestion" value="Ask more questions">
</form>
</p>
</body>
</html>
