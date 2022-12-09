package dal.csci5308.project.group15.elearning.quiz;

import java.sql.*;
import java.util.*;

import dal.csci5308.project.group15.elearning.database.Database;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("createQuiz")
public class QuizController {
    QuizData quizdata = new QuizData();

    @GetMapping("/a")
    public String createQuiz(){
        return "createQuiz";
    }

    @GetMapping("/a/quiz")
    public String saveQuizGetMapping(Model model) {
        System.out.println("here");
        model.addAttribute("quiz", new Quiz());
        System.out.println(model);
        return "createQuiz";
    }

    @PostMapping("/a/saveQuiz")
    public String saveQuiz(@ModelAttribute("quiz") Quiz quiz, Model model) throws SQLException {
        System.out.println("here 2");

        quizdata.setQuizIdFk(quiz.quizId); //storing intermediate values as model value does not persist

        System.out.println(quiz.toString());
        Connection connection = Database.instance().getConnection();
        CallableStatement stmt = connection.prepareCall("call put_quiz_details(?,?,?,?,?)");
        stmt.setString(1, quiz.id);
        stmt.setString(2, quiz.quizId);
        stmt.setString(3, quiz.description);
        stmt.setDate(4, quiz.getStartDate());
        stmt.setDate(5, quiz.getEndDate());

        int result = stmt.executeUpdate();
        System.out.println(result);
        connection.commit();
        connection.close();

        return "createQuizQuestion";
    }

    @PostMapping("/a/saveQuizQuestion")
    public String saveQuizQuestion(@ModelAttribute("quiz") Quiz quiz, Model model) throws SQLException {

        quizdata.setQuestionIdFk(quiz.questionId); //storing intermediate values as model value does not persist

        System.out.println(quiz.toString());
        Connection connection = Database.instance().getConnection();;
        CallableStatement stmt1 = connection.prepareCall("call put_questions_details(?,?,?)");
        stmt1.setString(1, quiz.questionId);
        stmt1.setString(2, quizdata.getQuizIdFk());
        stmt1.setString(3, quiz.question);
        System.out.println("question details " +quiz.questionId + quizdata.getQuizIdFk() + quiz.question);

        int result1 = stmt1.executeUpdate();

        CallableStatement stmt2 = connection.prepareCall("call put_answer_details(?,?,?,?,?,?)");
        stmt2.setString(1, quizdata.getQuestionIdFk());
        stmt2.setString(2, quiz.option1);
        stmt2.setString(3, quiz.option2);
        stmt2.setString(4, quiz.option3);
        stmt2.setString(5, quiz.option4);
        stmt2.setString(6, quiz.answer);
        int result2 = stmt2.executeUpdate();

        connection.commit();
        connection.close();

            return "addQuestion";

    }
    @PostMapping("/a/newQuestion")
    public String newQuestion(@ModelAttribute("quiz") Quiz quiz, Model model) throws SQLException {

        Connection connection = Database.instance().getConnection();
        Statement stmt = connection.createStatement();
        connection.commit();
        connection.close();
        return "createQuizQuestion";

    }
    @GetMapping("/a/getexitQuestion")
    public String getexitQuestion() throws SQLException {
        System.out.println("inside getExitQuestion");
        return "quizDefault";

    }
    @PostMapping("/a/postexitQuestion")
    public String postexitQuestion(@ModelAttribute("quiz") Quiz quiz, Model model) throws SQLException {
        return "quizDefault";

    }

}
