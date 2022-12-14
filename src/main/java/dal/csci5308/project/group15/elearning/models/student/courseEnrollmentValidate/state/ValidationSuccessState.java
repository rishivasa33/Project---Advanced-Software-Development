package dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state;

public class ValidationSuccessState implements IValidationState {

    String validationResult = "SUCCESS";

    @Override
    public Boolean isValid() {
        return true;
    }

    @Override
    public String getValidationResult() {
        return this.validationResult;
    }

    @Override
    public String setValidationResult(String validationResult) {
        return this.validationResult;
    }
}
