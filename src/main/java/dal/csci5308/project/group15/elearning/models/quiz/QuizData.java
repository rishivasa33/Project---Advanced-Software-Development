package dal.csci5308.project.group15.elearning.models.quiz;

public class QuizData {

    private String questionIdFk;
    private String quizIdFk;
    public QuizData() {
    }

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
