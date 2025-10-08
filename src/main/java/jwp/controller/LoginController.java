package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        if (user!=null && password.equals(user.getPassword())){
            System.out.println("user login: " + userId);
            resp.sendRedirect("/");

            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        }
        else{
            System.out.println("login failed: " + userId);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user/loginFailed.jsp");
            requestDispatcher.forward(req, resp);

            HttpSession session = req.getSession();
            session.removeAttribute("user");
        }
    }
}
