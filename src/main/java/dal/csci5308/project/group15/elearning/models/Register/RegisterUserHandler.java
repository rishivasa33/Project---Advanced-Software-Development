package dal.csci5308.project.group15.elearning.models.Register;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.encoder.EncoderFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.security.IEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterUserHandler implements IRegisterUserHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public RegisterUserHandler() {
    }

    @Override
    public int createNewUser(IDatabaseOperations databaseOperations, User user)
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

    @Override
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
