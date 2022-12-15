package dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state;

public class ValidationFailedState implements IValidationState {

    String validationResult = "";

    @Override
    public Boolean isValid() {
        return false;
    }

    @Override
    public String getValidationResult() {
        return this.validationResult;
    }

    @Override
    public String setValidationResult(String errorMessage) {
        this.validationResult = errorMessage;
        return errorMessage;
    }
}
