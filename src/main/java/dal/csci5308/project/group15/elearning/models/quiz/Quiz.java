package dal.csci5308.project.group15.elearning.models.quiz;

import dal.csci5308.project.group15.elearning.persistence.QuizPersistence;
import dal.csci5308.project.group15.elearning.persistence.QuizPersistenceSingleton;
import dal.csci5308.project.group15.elearning.quiz.QuizParams;

import java.sql.Date;
import java.sql.SQLException;

public class Quiz {
    String id;
    String quizId;
    String name;
    String question;
    String description;
    int questionCount;
    String questionId;
    Date endDate;
    String answer;
    Date startDate;
    String option1;
    String option2;
    String option3;
    String option4;
    String quizIdFk;
    String questionIdFk;

    public String getQuizIdFk() {
        return quizIdFk;
    }

    public void setQuizIdFk(String quizIdFk) {
        this.quizIdFk = quizIdFk;
    }

    public String getQuestionIdFk() {
        return questionIdFk;
    }

    public void setQuestionIdFk(String questionIdFk) {
        this.questionIdFk = questionIdFk;
    }



    QuizPersistence quizPersistence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public Quiz(QuizParams quizObj){
        id = quizObj.getId();
        quizId = quizObj.getQuizId();
        name = quizObj.getName();
        question = quizObj.getQuestion();
        description = quizObj.getDescription();
        questionId = quizObj.getQuestionId();
        answer = quizObj.getAnswer();
        endDate = quizObj.getEndDate();
        startDate = quizObj.getStartDate();
        option1 = quizObj.getOption1();
        option2 = quizObj.getOption2();
        option3 = quizObj.getOption3();
        option4 = quizObj.getOption4();


        quizPersistence = QuizPersistenceSingleton.GetQuizPersistence();
    }

    public Quiz(){
        quizPersistence = QuizPersistenceSingleton.GetQuizPersistence();
    }

    public void SaveQuizInfo() throws SQLException {
       quizPersistence.saveQuizInfo(this);
    }



    public void saveQuizQuestion(String quizIdFk, String questionIdFk) throws SQLException {
        quizPersistence.saveQuizQuestion(this,quizIdFk,questionIdFk);
    }
}
