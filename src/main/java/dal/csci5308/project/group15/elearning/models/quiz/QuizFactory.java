package dal.csci5308.project.group15.elearning.models.quiz;

public class QuizFactory implements IQuizFactory {
    @Override
    public Quiz createQuiz(QuizParams quizparams) {
        return new Quiz(quizparams);
    }

    @Override
    public QuizData quizData() {
        return new QuizData();
    }
}
