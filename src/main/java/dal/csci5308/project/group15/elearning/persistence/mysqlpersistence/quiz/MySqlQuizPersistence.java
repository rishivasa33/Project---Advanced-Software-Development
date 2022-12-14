package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.quiz;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.quiz.Quiz;
import dal.csci5308.project.group15.elearning.persistence.QuizPersistence;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlQuizPersistence implements QuizPersistence {

    public MySqlQuizPersistence() {
    }

    @Override
    public void saveQuizInfo(Quiz quiz) throws SQLException {
        Connection connection = Database.instance().getConnection();
        CallableStatement stmt = connection.prepareCall("call put_quiz_details(?,?,?,?,?)");
        stmt.setString(1, quiz.getId());
        stmt.setString(2, quiz.getQuizId());
        stmt.setString(3, quiz.getDescription());
        stmt.setDate(4, quiz.getStartDate());
        stmt.setDate(5, quiz.getEndDate());
        stmt.executeUpdate();
        connection.commit();
        connection.close();
    }

    @Override
    public void saveQuizQuestion(Quiz quiz, String quizIdFk, String questionIdFk) throws SQLException {

        Connection connection = Database.instance().getConnection();
        CallableStatement stmt1 = connection.prepareCall("call put_questions_details(?,?,?)");
        stmt1.setString(1, quiz.getQuestionId());
        stmt1.setString(2, quizIdFk);
        stmt1.setString(3, quiz.getQuestion());

        stmt1.executeUpdate();

        CallableStatement stmt2 = connection.prepareCall("call put_answer_details(?,?,?,?,?,?)");
        stmt2.setString(1, questionIdFk);
        stmt2.setString(2, quiz.getOption1());
        stmt2.setString(3, quiz.getOption2());
        stmt2.setString(4, quiz.getOption3());
        stmt2.setString(5, quiz.getOption4());
        stmt2.setString(6, quiz.getAnswer());
        stmt2.executeUpdate();

        connection.commit();
        connection.close();
    }
}
