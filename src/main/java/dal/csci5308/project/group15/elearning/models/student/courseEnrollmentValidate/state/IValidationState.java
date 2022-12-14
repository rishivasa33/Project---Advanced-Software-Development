package dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state;

public interface IValidationState {

    Boolean isValid();
    String getValidationResult();

    String setValidationResult(String validationResult);
}
