package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.quiz.Quiz;
import dal.csci5308.project.group15.elearning.persistence.QuizPersistence;
import dal.csci5308.project.group15.elearning.persistence.QuizPersistenceSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class CreateQuizTest {

    @Test
    void TestsaveQuizInfo() throws SQLException{
            try{

                Quiz quiz = new Quiz();
                String value = "test";
                quiz.setQuizId(value);
                quiz.setQuizIdFk(value);
                quiz.setAnswer(value);
                quiz.setDescription(value);
                quiz.SaveQuizInfo();
                Assertions.assertTrue(true);
            }
            catch(Exception e){
                Assertions.fail();
            }
    }


    @Test
    void TestSaveQuizQuestions() throws SQLException{
        try{

            Quiz quiz = new Quiz();
            String value = "test";
            quiz.setQuizId(value);
            quiz.setQuizIdFk(value);
            quiz.setAnswer(value);
            quiz.setDescription(value);
            QuizPersistence quizPersistence = QuizPersistenceSingleton.GetMockDBQuizPersistenceInstance();
            quizPersistence.saveQuizQuestion(quiz, "test", "test");
            Assertions.assertTrue(true);
        }
        catch(Exception e){
            Assertions.fail();
        }
    }

    @Test
    void TestQuizInfoCreation() throws SQLException{
        try{

            Quiz quiz = new Quiz();
            String value = "test2";
            quiz.setQuizId(value);
            quiz.setQuizIdFk(value);
            quiz.SaveQuizInfo();
            Assertions.assertTrue(true);
            Assertions.assertEquals(quiz.getQuizIdFk(), value);
            Assertions.assertEquals(quiz.getQuizId(), value);;

        }
        catch(Exception e){
            Assertions.fail();
        }
    }





    @Test
    void TestQuizInfoAttributes() throws SQLException{
        try{

            Quiz quiz = new Quiz();
            String value = "test";
            quiz.setQuizId(value);
            quiz.setQuizIdFk(value);
            quiz.setAnswer(value);
            quiz.setDescription(value);
            quiz.SaveQuizInfo();
            Assertions.assertTrue(true);
            Assertions.assertEquals(quiz.getQuizIdFk(), value);
            Assertions.assertEquals(quiz.getQuizId(), value);
            Assertions.assertEquals(quiz.getAnswer(), value);
            Assertions.assertEquals(quiz.getDescription(), value);


        }
        catch(Exception e){
            Assertions.fail();
        }
    }




}
