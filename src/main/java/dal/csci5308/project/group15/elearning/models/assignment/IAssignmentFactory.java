package dal.csci5308.project.group15.elearning.models.assignment;

public interface IAssignmentFactory {
    Assignment createAssignment(AssignmentParams assignmentparams);

    Assignment createAssignment();
}
