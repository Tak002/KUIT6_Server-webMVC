package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController implements Controller{
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = UserDao.getInstance().findUserById(userId);
        if (user!=null && password.equals(user.getPassword())){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return "redirect:" + "/";
        }
        else{
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            return "/user/loginFailed.jsp";
        }
    }
}
