package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowQuestionController implements  Controller {

    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        Question question = QuestionDao.getInstance().findQuestionById(questionId);
        System.out.println("question = " + question);
        req.setAttribute("question", question);
        return "/qna/show.jsp";
    }
}
