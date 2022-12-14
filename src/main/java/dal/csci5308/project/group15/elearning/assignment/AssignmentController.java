package dal.csci5308.project.group15.elearning.assignment;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import dal.csci5308.project.group15.elearning.models.assignment.IAssignmentFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@SessionAttributes({"assignmentId","student_number"})

public class AssignmentController {

    @GetMapping("/assignment")
    public String getAssignmentPage(Model model) {
        model.addAttribute("assignment", new AssignmentParams());
        return "createAssignment";
    }

    @PostMapping("/saveAssignment")
    public String postAssignment(@ModelAttribute("assignment") AssignmentParams assignment, Model model) throws SQLException, IOException {

       // String fileName = StringUtils.cleanPath(assignment.getFile().getOriginalFilename());

        //Assignment assignment_model = new Assignment(assignment);
        IAssignmentFactory assignmentFactory = FactoryFacade.instance().getAssignmentFactory();
        Assignment assignment_model = assignmentFactory.createAssignment(assignment);
        try {
            assignment_model.Save();
            return "assignmentDefault";
        } catch (SQLException exception) {
            return "assignmentDefault";
        }

    }

    @GetMapping("/loadAssignment/{courseInstanceID}")
    public ModelAndView loadAssignment(@PathVariable String courseInstanceID) {


        ModelAndView model = new ModelAndView("viewAssignment");
        //Assignment assignment_model = new Assignment();
        IAssignmentFactory assignmentFactory = FactoryFacade.instance().getAssignmentFactory();
        Assignment assignment_model = assignmentFactory.createAssignment();

        try {
            List<String> assignmentList = assignment_model.loadAssignmentList(courseInstanceID);
            System.out.println(assignmentList);
            model.addObject("assignmentList", assignmentList);
            return model;
        } catch (SQLException exception) {
            return model;
        }
    }

    @GetMapping("/loadAssignmentDetails/{assignmentId}")
    public String loadAssignmentDetails(@PathVariable String assignmentId,@ModelAttribute("assignment") AssignmentParams assignment ,Model model) {

        model.addAttribute(assignment);
        model.addAttribute("assignmentId",assignmentId);
       // Assignment assignment_model = new Assignment();
        IAssignmentFactory assignmentFactory = FactoryFacade.instance().getAssignmentFactory();
        Assignment assignment_model = assignmentFactory.createAssignment();
        try {
            List<Assignment> assignmentDetails = assignment_model.loadAssignmentDetails(assignmentId);
            model.addAttribute("assignmentDetails", assignmentDetails);
            System.out.println("Size of array" + assignmentDetails.size());
            return "viewAssignmentDetails";
        } catch (SQLException exception) {
            exception.printStackTrace();
            return "viewAssignmentDetails";
        }
    }

    @PostMapping("/student/submitStudentAssignment")
    public String submitStudentAssignment( @ModelAttribute("assignment") AssignmentParams assignment, Model model) throws SQLException {

        String assignment_id = String.valueOf(model.getAttribute("assignmentId"));
        String student_number = String.valueOf(model.getAttribute("student_number"));
        System.out.println("Debug session attributes " + assignment_id + "  " +student_number);

        IAssignmentFactory assignmentFactory = FactoryFacade.instance().getAssignmentFactory();
        Assignment assignment_model = assignmentFactory.createAssignment(assignment);
        //Assignment assignment_model = new Assignment(assignment);
        assignment_model.SaveStudentAssignment(assignment_id, student_number);
        return "submittedAssignmentDefault";

    }






}
