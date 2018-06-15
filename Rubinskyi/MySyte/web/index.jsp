<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questionnaire</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="shortcut icon" href="img/Question.png" type="image/png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/index.css"/>
</head>
<body>
<div class = "global_wrapper">

    <div class="header">
        <div class="hello_user">
            <form action="logout" method="get">
                <div class="user_name">
                    Hello, <%=session.getAttribute("login")%>
                </div>
                <button class="btn btn-primary" type="submit">log out</button>
            </form>
        </div>
    </div>

    <div class="main_content">
        <form:form class = "sending_form" method="post" action = "send" modelAttribute="quizToAdd">
            <p>Subject:<br>
                <input type="text" name="quizSubject" size="40">
            </p>
            <p>Theme:<br>
                <input type="text" name="quizTopic" size="40">
            </p>
            <button class="btn btn-primary" type="submit">Create a quiz</button>
        </form:form>

         <%--form action="show_all" method="get">
            <p>   <button class="btn btn-primary" type="submit">Show all quiz's</button> </p>
        </form>--%>

       <%-- <form action="delete" method="get">
            <p>id:<br>
                <input type="text" name="id" size="40">
            </p>
            <p> <button class="btn btn-primary" type="submit" value="Delete">Delete a quiz</button> </p>
        </form>--%>

        <table>
            <tr>
                <td>Id</td>
                <td>Theme</td>
                <td>Subject</td>
                <td>Creator</td>
                <td>Actions</td>
            </tr>
            <c:forEach items="${all_questions}" var="quizTopic">
                <tr class="table">
                    <td class="table"> ${quizTopic.id}</td>
                    <td class="table"> ${quizTopic.quizSubject}</td>
                    <td class="table"> ${quizTopic.quizTopic}</td>
                    <td class="table"> ${quizTopic.idCreator}</td>
                    <td><input type="button" class="delete-button btn-danger" delete-id = "${quizTopic.id}" value="Delete"></td>
                </tr>
            </c:forEach>
        </table>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
               $(".delete-button").click(function () {
                   var deleteId = $(this).attr("delete-id");
                   $.ajax({
                       type: "DELETE",
                       url: "/delete/" + deleteId,
                       success: function () {
                           location.reload()
                       }
                   })
               })
            })
        </script>
    </div>

    <footer class="footer">
        <div class="container">
            <span class="text-muted">FOOTER</span>
        </div>
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>