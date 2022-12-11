package dal.csci5308.project.group15.elearning.models.Register;

public class User
{
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String defaultPassword;
    private String program;

    public User()
    {

    }

    public User(String firstName, String lastName, String userType, String email, String defaultPassword, String program) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = userType;
        this.email = email;
        this.defaultPassword = defaultPassword;
        this.program = program;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", defaultPassword='" + defaultPassword + '\'' +
                ", program='" + program + '\'' +
                '}';
    }
}
