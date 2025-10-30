package jwp.controller;

public class LoginFormController implements Controller{
    @Override
    public String handle(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        return "/user/login.jsp";
    }
}
