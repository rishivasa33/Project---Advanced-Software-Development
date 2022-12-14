package dal.csci5308.project.group15.elearning.controller.assignment;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import dal.csci5308.project.group15.elearning.models.assignment.AssignmentParams;
import dal.csci5308.project.group15.elearning.models.assignment.IAssignmentFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

@Controller
@SessionAttributes({"assignmentId", "student_number"})

public class AssignmentController {

    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

    @GetMapping("/assignment")
    public String getAssignmentPage(Model model) {
        model.addAttribute("assignment", new AssignmentParams());
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_CREATE_ASSIGNMENT");
    }

    @PostMapping("/saveAssignment")
    public String postAssignment(@ModelAttribute("assignment") AssignmentParams assignment){

        IAssignmentFactory assignmentFactory = FactoryFacade.instance().getAssignmentFactory();
        Assignment assignment_model = assignmentFactory.createAssignment(assignment);
        try {
            assignment_model.Save();
        } catch (SQLException exception) {
        }
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_ASSIGNMENT_DEFAULT");
    }

    @GetMapping("/loadAssignment/{courseInstanceID}")
    public ModelAndView loadAssignment(@PathVariable String courseInstanceID) {

        ModelAndView model = new ModelAndView("viewAssignment");
        IAssignmentFactory assignmentFactory = FactoryFacade.instance().getAssignmentFactory();
        Assignment assignment_model = assignmentFactory.createAssignment();

        try {
            List<String> assignmentList = assignment_model.loadAssignmentList(courseInstanceID);
            model.addObject("assignmentList", assignmentList);
            return model;
        } catch (SQLException exception) {
            return model;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/loadAssignmentDetails/{assignmentId}")
    public String loadAssignmentDetails(@PathVariable String assignmentId, @ModelAttribute("assignment") AssignmentParams assignment, Model model) {

        model.addAttribute(assignment);
        model.addAttribute("assignmentId", assignmentId);
        IAssignmentFactory assignmentFactory = FactoryFacade.instance().getAssignmentFactory();
        Assignment assignment_model = assignmentFactory.createAssignment();
        try {
            List<Assignment> assignmentDetails = assignment_model.loadAssignmentDetails(assignmentId);
            model.addAttribute("assignmentDetails", assignmentDetails);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_VIEW_ASSIGNMENT_DETAILS");
    }

    @PostMapping("/student/submitStudentAssignment")
    public String submitStudentAssignment(@ModelAttribute("assignment") AssignmentParams assignment, Model model) throws SQLException {

        String assignment_id = String.valueOf(model.getAttribute("assignmentId"));
        String student_number = String.valueOf(model.getAttribute("student_number"));

        IAssignmentFactory assignmentFactory = FactoryFacade.instance().getAssignmentFactory();
        Assignment assignment_model = assignmentFactory.createAssignment(assignment);
        assignment_model.SaveStudentAssignment(assignment_id, student_number);

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_SUBMITTED_ASSIGNMENT_DEFAULT");

    }
}
