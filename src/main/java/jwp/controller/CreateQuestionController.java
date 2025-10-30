package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.support.KeyHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateQuestionController implements Controller {
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        KeyHolder keyHolder = new KeyHolder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdDate = LocalDateTime.now().format(formatter);
        QuestionDao.getInstance().insert(new Question(null, writer, title, contents,createdDate , 0),keyHolder);

        Question question = QuestionDao.getInstance().findQuestionById(keyHolder.getId());
        if(question == null){
            return "redirect:/";
        }
        return "redirect:/qna/show?questionId=" + question.getQuestionId();
    }
}
