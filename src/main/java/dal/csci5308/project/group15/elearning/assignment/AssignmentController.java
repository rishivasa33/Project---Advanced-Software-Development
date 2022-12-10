package dal.csci5308.project.group15.elearning.assignment;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.sql.*;

@Controller
public class AssignmentController {

    @GetMapping("/assignment")
    public String getAssignmentPage(Model model){
        model.addAttribute("assignment", new AssignmentParams());
        return "createAssignment";
    }

    @PostMapping("/saveAssignment")
    public String postAssignment(@ModelAttribute("assignment") AssignmentParams assignment, Model model) throws SQLException, IOException {

        System.out.println("Debug 1 "+assignment.getFile().getName() +" "+assignment.getFile().getSize());
        String fileName = StringUtils.cleanPath(assignment.getFile().getOriginalFilename());

        Assignment assignment_model = new Assignment(assignment);
        try {
            assignment_model.Save();
            return "quizDefault";
        }
        catch (SQLException exception){
            return "quizDefault";
        }

    }
}
