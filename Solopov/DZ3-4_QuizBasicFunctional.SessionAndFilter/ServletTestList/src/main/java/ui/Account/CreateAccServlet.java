package ui.Account;

import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateAccServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session=req.getSession();
        String login,password;
        login=req.getParameter("uname");
        password=req.getParameter("psw");

        if(session.getAttribute("login")==null){
            if(login!=null && password!=null){
                boolean isCreateAcc = new UserService().addAcc(login, password);
                if (isCreateAcc){ session.setAttribute("login", login);
                    resp.sendRedirect("/quizlist");
                }
                else req.getRequestDispatcher("Registration.jsp").forward(req,resp);

            }
            else req.getRequestDispatcher("Registration.jsp").forward(req,resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
