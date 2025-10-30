package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController implements Controller{
	@Override
	public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		List<Question> questions = QuestionDao.getInstance().findAll();
		req.setAttribute("questions",questions);
		return "/home.jsp";
	}
}
