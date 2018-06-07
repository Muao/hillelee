package com.kuznetsov.controllers;

import com.kuznetsov.dao.impl.QuizDaoHibernate;
import com.kuznetsov.entities.UserDataFromLoginJSP;
import com.kuznetsov.entities.UsersEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/")

public class LoginController {

    @Autowired
    private QuizDaoHibernate quizDao;

    @Autowired
    private UsersEntity usersEntity;

    @RequestMapping(method = GET, value = "")
    public void getLoginView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher loginDispatcher = req.getRequestDispatcher("/view/login.jsp");

        HttpSession session = req.getSession();

        if (session.getAttribute("wrongMessage") == null) {
            session.setAttribute("wrongMessage", "");
        }
        loginDispatcher.include(req, resp);
    }


    @RequestMapping(method = POST, value = "")
    public void userDataProcessing(@ModelAttribute("userDataFromLoginJSP") UserDataFromLoginJSP userDataFromLoginJSP, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String buttonType = userDataFromLoginJSP.getSubmit();

        if (buttonType.equals("Sign up")) {
            signUpButtonAction(userDataFromLoginJSP, req, resp);
        }
        if (buttonType.equals("Sign in")) {
            signInButtonAction(userDataFromLoginJSP, req, resp);
        }
    }

    private void signUpButtonAction(UserDataFromLoginJSP userDataFromLoginJSP, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        boolean userExist = quizDao.getUserFromDB(userDataFromLoginJSP.getLogin()) != null;

        if (!userExist) {
            String salt = BCrypt.gensalt();
            String pwd = BCrypt.hashpw(userDataFromLoginJSP.getPwd(), salt);

            usersEntity = new UsersEntity(userDataFromLoginJSP.getLogin(), pwd, salt);

            quizDao.saveCredentialsToDB(usersEntity);
            setCredentialsToSession(usersEntity, req);
            resp.sendRedirect("/quiz");

        } else {
            String wrongMessage = "<p>Username already exist</p>";
            req.getSession().setAttribute("wrongMessage", wrongMessage);
            getLoginView(req, resp);
        }
    }

    private void signInButtonAction(UserDataFromLoginJSP userDataFromLoginJSP, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Boolean checkPwd = false;
        usersEntity = quizDao.getUserFromDB(userDataFromLoginJSP.getLogin());

        if (usersEntity != null) {
            checkPwd = (usersEntity.getPwd().equals(BCrypt.hashpw(userDataFromLoginJSP.getPwd(), usersEntity.getSalt())));
        }

        if (checkPwd) {
            setCredentialsToSession(usersEntity, req);
            resp.sendRedirect("/quiz");
        } else {
            setWrongMessageToLoginJSP(req, resp);
        }
    }

    private void setWrongMessageToLoginJSP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String wrongMessage = "<p>Your login or password are wrong. Try again.</p> <p>New user - press Sign up</p>";
        req.getSession().setAttribute("wrongMessage", wrongMessage);

        getLoginView(req, resp);
    }

    private void setCredentialsToSession(UsersEntity usersEntity, HttpServletRequest req) throws IOException {

        req.getSession().setAttribute("login", usersEntity.getLogin());
        req.getSession().setAttribute("pwd", usersEntity.getPwd());
    }
}
