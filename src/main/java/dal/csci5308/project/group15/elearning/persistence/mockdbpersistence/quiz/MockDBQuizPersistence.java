package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.quiz;

import dal.csci5308.project.group15.elearning.models.quiz.Quiz;
import dal.csci5308.project.group15.elearning.persistence.QuizPersistence;

import java.sql.SQLException;

public class MockDBQuizPersistence implements QuizPersistence {

    public MockDBQuizPersistence(){

    }

    @Override
    public void saveQuizInfo(Quiz quiz) throws SQLException {

    }

    @Override
    public void saveQuizQuestion(Quiz quiz, String quizIdFk, String questionIdFk) {


    }


}
