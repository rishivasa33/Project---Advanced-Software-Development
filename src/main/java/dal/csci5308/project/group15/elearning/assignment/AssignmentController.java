package dal.csci5308.project.group15.elearning.assignment;

import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class AssignmentController {

    @GetMapping("/assignment")
    public String getAssignmentPage(Model model) {
        model.addAttribute("assignment", new AssignmentParams());
        return "createAssignment";
    }

    @PostMapping("/saveAssignment")
    public String postAssignment(@ModelAttribute("assignment") AssignmentParams assignment, Model model) throws SQLException, IOException {

        String fileName = StringUtils.cleanPath(assignment.getFile().getOriginalFilename());

        Assignment assignment_model = new Assignment(assignment);
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
        Assignment assignment_model = new Assignment();
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
    public String loadAssignmentDetails(@PathVariable String assignmentId, Model model) {

        Assignment assignment_model = new Assignment();
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


}
