package dal.csci5308.project.group15.elearning.quiz;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.quiz.IQuizFactory;
import dal.csci5308.project.group15.elearning.models.quiz.Quiz;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class QuizController {
    IQuizFactory quizFactory = FactoryFacade.instance().getQuizFactory();
    QuizData quizData = quizFactory.quizData();

    @GetMapping("/a")
    public String createQuiz() {
        return "createQuiz";
    }

    @GetMapping("/quiz")
    public String saveQuizGetMapping(Model model) {
        model.addAttribute("quiz", new QuizParams());
        return "createQuiz";
    }

    @PostMapping("/saveQuiz")
    public String saveQuiz(@ModelAttribute("quiz") QuizParams quiz, Model model) throws SQLException {

        quizData.setQuizIdFk(quiz.quizId);
        IQuizFactory quizFactory = FactoryFacade.instance().getQuizFactory();
        Quiz quizModel = quizFactory.createQuiz(quiz);
        quizModel.SaveQuizInfo();
        return "createQuizQuestion";
    }

    @PostMapping("/saveQuizQuestion")
    public String saveQuizQuestion(@ModelAttribute("quiz") QuizParams quiz, Model model) throws SQLException {

        quizData.setQuestionIdFk(quiz.questionId);
        IQuizFactory quizFactory = FactoryFacade.instance().getQuizFactory();
        Quiz quizModel = quizFactory.createQuiz(quiz);
        quizModel.saveQuizQuestion(quizData.getQuizIdFk(), quizData.getQuestionIdFk());
        return "addQuestion";

    }

    @PostMapping("/newQuestion")
    public String newQuestion(@ModelAttribute("quiz") QuizParams quiz, Model model) throws SQLException {

        Connection connection = Database.instance().getConnection();
        Statement stmt = connection.createStatement();
        connection.commit();
        connection.close();
        return "createQuizQuestion";
    }

    @GetMapping("/getexitQuestion")
    public String getexitQuestion() throws SQLException {
        return "quizDefault";

    }

    @PostMapping("/postexitQuestion")
    public String postexitQuestion(@ModelAttribute("quiz") QuizParams quiz, Model model) throws SQLException {
        return "quizDefault";

    }

}
