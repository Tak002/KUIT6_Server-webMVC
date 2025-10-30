package jwp.model;

public class Question {
    private final Long questionId;
    private final String writer;
    private final String title;
    private final String contents;
    private final String createdDate;
    private final int countOfAnswer;
    public Question(Long questionId, String writer, String title, String contents, String createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Long getQuestionId() {
        return questionId;
    }
    public String getWriter() {
        return writer;
    }
    public String getTitle() {
        return title;
    }
    public String getContents() {
        return contents;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public int getCountOfAnswer() {
        return countOfAnswer;
    }
    public String toString(){
        return "Question{" +
                "questionId=" + questionId +
                ", writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", countOfAnswer=" + countOfAnswer +
                '}';
    }
}