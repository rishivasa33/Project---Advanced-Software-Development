package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.student;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.student.IStudentDetails;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.student.StudentDetails;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentDetailsPersistence;

public class MockStudentDetails implements IStudentDetailsPersistence {
    @Override
    public void save(StudentDetails studentDetails) {

    }

    @Override
    public IStudentDetails loadByStudentID(Integer userID) {

        IStudentDetails studentDetails;
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();

        studentDetails = studentFactory.createStudentDetailsInstance(userID, "B00909090", "MACS");
        return studentDetails;
    }

    @Override
    public IStudentDetails loadByStudentName(String userName) {

        IStudentDetails studentDetails;
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();

        studentDetails = studentFactory.createStudentDetailsInstance(2, "B00909090", "MACS");
        return studentDetails;
    }
}
