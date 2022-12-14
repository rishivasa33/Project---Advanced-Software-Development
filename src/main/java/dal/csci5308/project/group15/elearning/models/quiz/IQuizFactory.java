package dal.csci5308.project.group15.elearning.models.quiz;

import dal.csci5308.project.group15.elearning.quiz.QuizData;
import dal.csci5308.project.group15.elearning.quiz.QuizParams;

public interface IQuizFactory {

    Quiz createQuiz(QuizParams quizparams);

    QuizData quizData();
}
