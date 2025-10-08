package jwp.support;

import jwp.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private final RequestMapper requestMapper;

    public DispatcherServlet() {
        this.requestMapper = new RequestMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        System.out.println("requestURI = " + requestURI);
        System.out.println("method = " + method);
        Controller controller = requestMapper.getMapping(requestURI, method);
        String view = process(req, resp, controller);
        renderView(req, resp, view);
    }

    private String process(HttpServletRequest req, HttpServletResponse resp, Controller controller) throws ServletException, IOException {
        if(controller == null) {
            return null;
        }
        return controller.handle(req, resp);
    }

    private void renderView(HttpServletRequest req, HttpServletResponse resp, String view) throws IOException, ServletException {
        if(view == null) {return;}

        if (view.startsWith("redirect")) {
            resp.sendRedirect(view.substring("redirect:".length()));
        }else{
            req.getRequestDispatcher(view).forward(req, resp);
        }
    }
}
