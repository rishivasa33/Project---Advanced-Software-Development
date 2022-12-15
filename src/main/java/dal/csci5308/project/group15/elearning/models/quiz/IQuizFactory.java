package dal.csci5308.project.group15.elearning.models.quiz;

public interface IQuizFactory {

    Quiz createQuiz(QuizParams quizparams);

    QuizData quizData();
}
