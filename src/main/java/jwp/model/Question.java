package jwp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "QUESTIONS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    @Id
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private String createdDate;
    private int countOfAnswer;
}