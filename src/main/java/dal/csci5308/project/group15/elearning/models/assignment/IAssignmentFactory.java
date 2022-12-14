package dal.csci5308.project.group15.elearning.models.assignment;

import dal.csci5308.project.group15.elearning.assignment.AssignmentParams;

public interface IAssignmentFactory {
    Assignment createAssignment(AssignmentParams assignmentparams);

    Assignment createAssignment();
}
