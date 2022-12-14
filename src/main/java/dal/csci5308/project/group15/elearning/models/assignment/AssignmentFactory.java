package dal.csci5308.project.group15.elearning.models.assignment;

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
