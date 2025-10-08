package jwp.controller;

import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserUpdateFormController implements Controller{
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        String userId = req.getParameter("userId");

        if(user != null && user.getUserId().equals(userId)){
            return "/user/updateForm.jsp";
        }
        else{
            return "redirect:" + "/";
        }
    }
}
