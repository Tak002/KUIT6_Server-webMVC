package jwp.controller;

import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CreateQuestionFormController implements Controller {
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("user = " + user);
        if (user!= null) {
            return "/qna/form.jsp";
        }
        return "redirect:/user/loginForm";
    }
}
