package dal.csci5308.project.group15.elearning.models.quiz;

import dal.csci5308.project.group15.elearning.quiz.QuizData;
import dal.csci5308.project.group15.elearning.quiz.QuizParams;

public class QuizFactory implements IQuizFactory{
    @Override
    public Quiz createQuiz(QuizParams quizparams) {
        return new Quiz(quizparams);
    }

    @Override
    public QuizData quizData() {
        return new QuizData();
    }
}
