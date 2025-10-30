package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateUserController implements Controller{
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User userById = UserDao.getInstance().findUserById(userId);
        userById.update(new User(userId, password, name, email));

        return "redirect:" + "/user/list";
    }
}
