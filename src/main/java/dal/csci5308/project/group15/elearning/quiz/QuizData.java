package dal.csci5308.project.group15.elearning.quiz;

public class QuizData {

    public QuizData() {
    }

    private String questionIdFk;
    private String quizIdFk;

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
}
