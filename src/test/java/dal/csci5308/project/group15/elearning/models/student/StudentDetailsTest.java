package dal.csci5308.project.group15.elearning.models.student;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentDetailsPersistence;
import dal.csci5308.project.group15.elearning.persistence.student.StudentDetailsSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class StudentDetailsTest {

    @Test
    void createStudentDetailsInstanceTest() {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createStudentDetailsInstance(2, "B00909090", "MACS");

        assertEquals(studentDetails.getStudentUserID(), 2);
        assertEquals(studentDetails.getStudentNumber(), "B00909090");
        assertEquals(studentDetails.getStudentProgram(), "MACS");

    }

    @Test
    void createEmptyStudentDetailsInstanceTest() {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createEmptyStudentDetailsInstance();

        assertNull(studentDetails.getStudentUserID());
        assertNull(studentDetails.getStudentNumber());
        assertNull(studentDetails.getStudentUserID());

    }

    @Test
    void getStudentUserIDTest() {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createStudentDetailsInstance(2, "B00909090", "MACS");

        assertEquals(studentDetails.getStudentUserID(), 2);
    }

    @Test
    void getStudentNumberTest() {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createStudentDetailsInstance(2, "B00909090", "MACS");

        assertEquals(studentDetails.getStudentNumber(), "B00909090");
    }

    @Test
    void getStudentProgramTest() {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createStudentDetailsInstance(2, "B00909090", "MACS");

        assertEquals(studentDetails.getStudentProgram(), "MACS");
    }

    @Test
    void saveTest() {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createStudentDetailsInstance(2, "B00909090", "MACS");
        IStudentDetailsPersistence studentDetailsPersistence = StudentDetailsSingleton.GetMockStudentDetailsInstance();

        studentDetailsPersistence.save((StudentDetails) studentDetails);
        Assertions.assertTrue(true);
    }

    @Test
    void loadByUserIDTest() {
        Integer userID = 2;
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createEmptyStudentDetailsInstance();
        IStudentDetailsPersistence studentDetailsPersistence = StudentDetailsSingleton.GetMockStudentDetailsInstance();

        studentDetails = studentDetails.loadByUserID(studentDetailsPersistence, 2);
        assertEquals(studentDetails.getStudentUserID(), 2);
        assertEquals(studentDetails.getStudentNumber(), "B00909090");
        assertEquals(studentDetails.getStudentProgram(), "MACS");
    }

    @Test
    void loadByUserNameTest() {
        String userName = "JDoe";
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createEmptyStudentDetailsInstance();
        IStudentDetailsPersistence studentDetailsPersistence = StudentDetailsSingleton.GetMockStudentDetailsInstance();

        studentDetails = studentDetails.loadByUserName(studentDetailsPersistence, userName);
        assertEquals(studentDetails.getStudentUserID(), 2);
        assertEquals(studentDetails.getStudentNumber(), "B00909090");
        assertEquals(studentDetails.getStudentProgram(), "MACS");
    }
}