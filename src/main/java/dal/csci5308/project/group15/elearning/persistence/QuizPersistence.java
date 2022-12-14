package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.quiz.Quiz;
import java.sql.SQLException;

public interface QuizPersistence {

    void saveQuizInfo(Quiz quiz) throws SQLException;

    void saveQuizQuestion(Quiz quiz, String quizIdFk, String questionIdFk) throws SQLException;
}
