package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


public class ListUserController implements Controller{
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Object user = req.getSession().getAttribute("user");
        if(user == null){
            return "redirect:" + "/";
        }
        Collection<User> users = UserDao.getInstance().findAll();
         req.setAttribute("users", users);
        return "/user/list.jsp";
    }
}
