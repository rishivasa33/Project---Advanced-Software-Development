package dal.csci5308.project.group15.elearning.models.register;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.encoder.EncoderFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.security.encoder.IEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterUser
{
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String defaultPassword;
    private String program;
    private Map<String, String> programMap;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public RegisterUser() {

    }

    public RegisterUser(String firstName, String lastName, String userType, String email, String defaultPassword, String program, Map<String, String> programMap) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = userType;
        this.email = email;
        this.defaultPassword = defaultPassword;
        this.program = program;
        this.programMap = programMap;
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

    public Map<String, String> getProgramMap() {
        return programMap;
    }

    public void setProgramMap(Map<String, String> programMap) {
        this.programMap = programMap;
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
                ", programMap=" + programMap +
                '}';
    }

    public int createNewUser(IDatabaseOperations databaseOperations, RegisterUser user)
    {
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        IEncoder encoder = EncoderFactory.instance().makeEncoder();
        String encodedPassword = encoder.encode(user.getDefaultPassword());
        String program = user.getProgram();
        user.setProgram(program.replace(",", ""));

        try {
            return databaseOperations.create(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_REGISTER_USER_STUDENT"),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    encodedPassword,
                    user.getProgram(),
                    user.getRole());
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            return 0;
        }
    }

    public Map<String, String> getAllProgramList(IDatabaseOperations databaseOperations) {
        Map<String, String> programMap = new HashMap<>();
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

        try {
            Map<String, List<Object>> resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_REGISTER_USER_GET_PROGRAM_LIST_ALL"));

            for (int row = 0; row < databaseOperations.getRowCount(resultSet); row++) {
                String programId = String.valueOf(databaseOperations.getValueAt(resultSet, "program_id", row));
                String programName = String.valueOf(databaseOperations.getValueAt(resultSet, "program_name", row));
                programMap.put(programId, programName);
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            logger.debug(Arrays.toString(sqlException.getStackTrace()));
        }

        return programMap;
    }
}
