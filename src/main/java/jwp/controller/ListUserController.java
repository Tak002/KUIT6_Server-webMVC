package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


public class ListUserController implements Controller{
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        if(user == null){
            resp.sendRedirect("/");
            return;
        }
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
         req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
