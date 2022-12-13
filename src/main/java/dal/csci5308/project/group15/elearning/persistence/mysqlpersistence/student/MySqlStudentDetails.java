package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.student;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.student.IStudentDetails;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.student.StudentDetails;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTermsFactory;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentDetailsPersistence;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySqlStudentDetails implements IStudentDetailsPersistence {

    IDatabaseOperations databaseOperations = DatabaseOperations.instance();
    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

    @Override
    public void save(StudentDetails studentDetails) {

    }

    @Override
    public IStudentDetails loadByStudentID(Integer userID) {
        Map<String, List<Object>> resultSet;
        IStudentDetails studentDetails;

        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_STUDENT_GET_BY_USER_ID"), userID);

            studentDetails = parseStudentFieldsToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentDetails;
    }

    @Override
    public IStudentDetails loadByStudentName(String userName) {
        Map<String, List<Object>> resultSet;
        IStudentDetails studentDetails;

        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_STUDENT_GET_BY_USER_NAME"), userName);

            studentDetails = parseStudentFieldsToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentDetails;
    }

    private IStudentDetails parseStudentFieldsToList(Map<String, List<Object>> resultSet) {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails student = null;
        for (int row = 0; row < databaseOperations.getRowCount(resultSet); row++) {
            Integer userID = (Integer) (databaseOperations.getValueAt(resultSet, "student_user_id", row));
            String studentNumber = String.valueOf(databaseOperations.getValueAt(resultSet, "student_number", row));
            String studentProgram = String.valueOf(databaseOperations.getValueAt(resultSet, "student_program", row));

            student = studentFactory.createStudentDetailsInstance(userID, studentNumber, studentProgram);
        }
        return student;
    }
}
