package dal.csci5308.project.group15.elearning.controller.quiz;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.quiz.IQuizFactory;
import dal.csci5308.project.group15.elearning.models.quiz.Quiz;
import dal.csci5308.project.group15.elearning.models.quiz.QuizData;
import dal.csci5308.project.group15.elearning.models.quiz.QuizParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.sql.SQLException;

@Controller
public class QuizController {

    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
    IQuizFactory quizFactory = FactoryFacade.instance().getQuizFactory();
    QuizData quizData = quizFactory.quizData();

    @GetMapping("/a")
    public String createQuiz() {
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_CREATE_QUIZ");
    }

    @GetMapping("/quiz")
    public String saveQuizGetMapping(Model model) {
        model.addAttribute("quiz", new QuizParams());
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_CREATE_QUIZ");
    }

    @PostMapping("/saveQuiz")
    public String saveQuiz(@ModelAttribute("quiz") QuizParams quiz) throws SQLException {

        quizData.setQuizIdFk(quiz.getQuizId());
        IQuizFactory quizFactory = FactoryFacade.instance().getQuizFactory();
        Quiz quizModel = quizFactory.createQuiz(quiz);
        quizModel.SaveQuizInfo();
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_CREATE_QUIZ_QUESTION");
    }

    @PostMapping("/saveQuizQuestion")
    public String saveQuizQuestion(@ModelAttribute("quiz") QuizParams quiz) throws SQLException {

        quizData.setQuestionIdFk(quiz.getQuestionId());
        IQuizFactory quizFactory = FactoryFacade.instance().getQuizFactory();
        Quiz quizModel = quizFactory.createQuiz(quiz);
        quizModel.saveQuizQuestion(quizData.getQuizIdFk(), quizData.getQuestionIdFk());
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_ADD_QUESTION");

    }

    @PostMapping("/newQuestion")
    public String newQuestion() throws SQLException {
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_CREATE_QUIZ_QUESTION");
    }

    @GetMapping("/getexitQuestion")
    public String getexitQuestion() throws SQLException {
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_QUIZ_DEFAULT");

    }

    @PostMapping("/postexitQuestion")
    public String postexitQuestion() throws SQLException {
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_QUIZ_DEFAULT");

    }

}
