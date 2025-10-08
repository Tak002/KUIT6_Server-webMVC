package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


public class ListUserController implements Controller{
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        if(user == null){
            return "redirect:" + "/";
        }
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
         req.setAttribute("users", users);
        return "/user/list.jsp";
    }
}
