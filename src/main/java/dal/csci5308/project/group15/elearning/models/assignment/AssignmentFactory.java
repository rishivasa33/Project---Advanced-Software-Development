package dal.csci5308.project.group15.elearning.models.assignment;

import dal.csci5308.project.group15.elearning.assignment.AssignmentParams;

public class AssignmentFactory implements IAssignmentFactory{

    @Override
    public Assignment createAssignment(AssignmentParams assignmentparams){


        return new Assignment(assignmentparams);
    }

    @Override
    public Assignment createAssignment() {
        return new Assignment();
    }


}
