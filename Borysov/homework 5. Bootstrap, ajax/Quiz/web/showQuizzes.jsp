<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 15.04.2018
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%@include file="/head.jsp"%>
<body>
<div id="main">
    <div id="header">
        <div id="logo">
            <div id="logo_text">
                <h1><a href="mainPage.jsp"><span class="logo_colour">Quiz</span></a></h1>
            </div>
        </div>
    </div>


    <div id="site_content">
        <div class="sidebar">
            <h3><a href="/logout">Log out</a></h3>
        </div>
        <div id="content">
            <table class="table table-striped  ">

                <tr>
                    <th>№</th>
                    <th>Subject</th>
                    <th>Theme</th>
                    <th>Added by</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${listOfQuizzes}" var="quiz" varStatus="status">
                    <tr>
                        <td align="center"><c:out value="${status.count}" /></td>
                        <td align="center">${quiz.nameOfSubject}</td>
                        <td align="center">${quiz.theme}</td>
                        <td align="center">${quiz.author}</td>
                        <c:url var="addQuestionLink" value="/addQuestionAndAnswers">
                            <c:param name="quiz_id" value="${quiz.id}" />
                        </c:url>
                        <td align="center"><a href="${addQuestionLink}">AddQuestion</a></td>
                        <td>
                            <a  class="delete${quiz.id}" href="/showQuizzes">Delete</a>
                            <script>
                                $(document).ready(function () {
                                    $(".delete${quiz.id}").click(function () {
                                        $.ajax({
                                            type: "delete",
                                            url:"/delete/${quiz.id}"
                                        })
                                    })
                                })
                            </script>
                        </td>

                    </tr>
                </c:forEach>
            </table>
            <button type="button" class="btn"> <a href="mainPage.jsp">Cancel</a></button>

        </div>
    </div>

    <div id="footer">
        Quizzes | <a href="#">1</a> | <a href="#">2</a> | <a href="#">3</a>
    </div>



</div>
</body>
</html>
