package dal.csci5308.project.group15.elearning.quiz;

import java.util.ArrayList;
import java.sql.Date;

public class Quiz {
    String id;
    String quizId;
    String name;
    String question;
    String description;
    int questionCount;
    String questionId;
    Date endDate;
    int markingType;



    String answer;
    Date startDate;

    String option1;

    String option2;

    String option3;

    String option4;

    public String getAnswer() {
        return answer;
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }



    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }



    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMarkingType() {
        return markingType;
    }

    public void setMarkingType(int markingType) {
        this.markingType = markingType;
    }


    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public Quiz() {

    }

    public Quiz(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", quizId='" + quizId + '\'' +
                ", name='" + name + '\'' +
                ", question='" + question + '\'' +
                ", description='" + description + '\'' +
                ", questionCount=" + questionCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", markingType=" + markingType +
                ", answer=" + answer +
                '}';
    }
}
