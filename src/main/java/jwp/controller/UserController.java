package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDao userDao;

    @GetMapping("/list")
    public String getUserList(HttpSession session, Model model) throws ServletException, IOException, SQLException {
        Object user = session.getAttribute("user");
        if(user == null){
            return "redirect:" + "/";
        }
        Collection<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }
    
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) throws ServletException, IOException, SQLException {

        System.out.println("user = " + user);
        userDao.insert(user);

        return "redirect:" + "/";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = userDao.findUserById(userId);
        if (user!=null && password.equals(user.getPassword())){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return "redirect:" + "/";
        }
        else{
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            return "user/loginFailed";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        return "redirect:" + "/";

    }

    @PostMapping("/update")
    public String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User userById = userDao.findUserById(userId);
        userById.update(new User(userId, password, name, email));

        return "redirect:" + "/user/list";
    }

    @GetMapping("/updateForm")
    public String getUserUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        String userId = req.getParameter("userId");

        if(user != null && user.getUserId().equals(userId)){
            return "user/updateForm";
        }
        else{
            return "redirect:" + "/";
        }
    }

    @GetMapping("/form)")
    public String getForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("user = " + user);
        if (user!= null) {
            return "qna/form";
        }
        return "redirect:/user/loginForm";
    }
}
