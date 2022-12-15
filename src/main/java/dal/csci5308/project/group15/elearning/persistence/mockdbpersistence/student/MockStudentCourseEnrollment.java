package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.student;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.student.StudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentCourseEnrollmentPersistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class MockStudentCourseEnrollment implements IStudentCourseEnrollmentPersistence {
    @Override
    public Integer save(StudentCourseEnrollment studentCourseEnrollment) throws SQLException {
        System.out.println("Save MockStudentCourseEnrollment");
        return 1;
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByStudentNumber(String studentNumber) throws SQLException, ParseException {
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = new ArrayList<>();
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();

        IStudentCourseEnrollment enrollment = studentFactory.createStudentCourseEnrollmentInstance("F22CSCI5308", studentNumber, "F22");
        studentCourseEnrollments.add(enrollment);

        enrollment = studentFactory.createStudentCourseEnrollmentInstance("W23CSCI6308", studentNumber, "W23");
        studentCourseEnrollments.add(enrollment);

        return studentCourseEnrollments;
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByCourseInstanceID(String courseInstanceId) throws SQLException, ParseException {
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = new ArrayList<>();
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();

        IStudentCourseEnrollment enrollment = studentFactory.createStudentCourseEnrollmentInstance(courseInstanceId, "B00909090", "F22");
        studentCourseEnrollments.add(enrollment);

        enrollment = studentFactory.createStudentCourseEnrollmentInstance(courseInstanceId, "B00812312", "F22");
        studentCourseEnrollments.add(enrollment);

        return studentCourseEnrollments;
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByTermAndStudentNumber(String studentNumber, String courseTerm) throws SQLException, ParseException {
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = new ArrayList<>();
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();

        IStudentCourseEnrollment enrollment = studentFactory.createStudentCourseEnrollmentInstance("F22CSCI5308", studentNumber, courseTerm);
        studentCourseEnrollments.add(enrollment);

        return studentCourseEnrollments;
    }

    @Override
    public Integer loadStudentCourseCountByTerm(String studentNumber, String courseTerm) {
        return 3;
    }

    @Override
    public Integer loadStudentCreditCountByTerm(String studentNumber, String courseTerm) {
        return 9;
    }
}
