package dal.csci5308.project.group15.elearning.factory;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollmentFactory;
import dal.csci5308.project.group15.elearning.models.student.StudentCourseEnrollmentFactory;

public class FactoryFacade {

    static FactoryFacade uniqueInstance = null;
    private static ICourseFactory courseFactory;

    private static IStudentCourseEnrollmentFactory studentCourseEnrollmentFactory;

    private FactoryFacade() {
        courseFactory = new CourseFactory();
        studentCourseEnrollmentFactory = new StudentCourseEnrollmentFactory();
    }

    public static FactoryFacade instance() {
        if (null == uniqueInstance) {
            System.out.println("Creating NEW single instance of FactoryFacade");

            uniqueInstance = new FactoryFacade();
            return uniqueInstance;
        }
        return uniqueInstance;
    }

    public ICourseFactory getCourseFactory() {
        return courseFactory;
    }

    public IStudentCourseEnrollmentFactory getStudentCourseEnrollmentFactory() {
        return studentCourseEnrollmentFactory;
    }

}
