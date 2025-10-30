package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserController implements Controller{
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        UserDao userRepository = UserDao.getInstance();
        userRepository.insert(user);

        return "redirect:" + "/";
    }
}
