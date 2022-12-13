package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import dal.csci5308.project.group15.elearning.models.quiz.Quiz;

import java.sql.SQLException;

public interface QuizPersistence {


    void saveQuizInfo(Quiz quiz) throws SQLException;



    public Quiz load(String quizId);


    void saveQuizQuestion(Quiz quiz, String quizIdFk, String questionIdFk) throws SQLException;
}
