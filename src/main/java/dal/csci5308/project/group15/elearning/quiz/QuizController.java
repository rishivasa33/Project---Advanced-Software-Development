package dal.csci5308.project.group15.elearning.quiz;

import java.sql.*;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import dal.csci5308.project.group15.elearning.models.assignment.IAssignmentFactory;
import dal.csci5308.project.group15.elearning.models.quiz.IQuizFactory;
import dal.csci5308.project.group15.elearning.models.quiz.Quiz;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuizController {
   // QuizData quizdata = new QuizData();
    IQuizFactory quizFactory = FactoryFacade.instance().getQuizFactory();
    QuizData quizData = quizFactory.quizData();


    @GetMapping("/a")
    public String createQuiz(){
        return "createQuiz";
    }

    @GetMapping("/quiz")
    public String saveQuizGetMapping(Model model) {
        System.out.println("here");
        model.addAttribute("quiz", new QuizParams());
        System.out.println(model);
        return "createQuiz";
    }

    @PostMapping("/saveQuiz")
    public String saveQuiz(@ModelAttribute("quiz") QuizParams quiz, Model model) throws SQLException {

        quizData.setQuizIdFk(quiz.quizId);
       // Quiz quizModel = new Quiz(quiz);
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
     //   Quiz quizModel = new Quiz(quiz);
        quizModel.saveQuizQuestion(quizData.getQuizIdFk(),quizData.getQuestionIdFk());

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
        System.out.println("inside getExitQuestion");
        return "quizDefault";

    }
    @PostMapping("/postexitQuestion")
    public String postexitQuestion(@ModelAttribute("quiz") QuizParams quiz, Model model) throws SQLException {
        return "quizDefault";

    }

}
