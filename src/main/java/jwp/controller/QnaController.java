package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.support.KeyHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QuestionDao questionDao;

    @GetMapping("/form")
    public String getQuestionForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("user = " + user);
        if (user!= null) {
            return "qna/form";
        }
        return "redirect:/user/loginForm";
    }

    @PostMapping("/create")
    public String createQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        KeyHolder keyHolder = new KeyHolder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdDate = LocalDateTime.now().format(formatter);
        questionDao.insert(new Question(null, writer, title, contents,createdDate , 0));

        Question question = questionDao.findQuestionById(keyHolder.getId());
        if(question == null){
            return "redirect:/";
        }
        return "redirect:/qna/show?questionId=" + question.getQuestionId();
    }

    @GetMapping("/show")
    public String getShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        Question question = questionDao.findQuestionById(questionId);
        System.out.println("question = " + question);
        req.setAttribute("question", question);
        return "qna/show";
    }
}
