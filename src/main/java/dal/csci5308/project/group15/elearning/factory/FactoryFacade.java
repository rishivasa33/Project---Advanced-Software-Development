package dal.csci5308.project.group15.elearning.factory;

import dal.csci5308.project.group15.elearning.models.assignment.AssignmentFactory;
import dal.csci5308.project.group15.elearning.models.assignment.IAssignmentFactory;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.models.quiz.IQuizFactory;
import dal.csci5308.project.group15.elearning.models.quiz.QuizFactory;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.student.StudentFactory;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTermsFactory;
import dal.csci5308.project.group15.elearning.models.terms.UniversityTermsFactory;

public class FactoryFacade {

    static FactoryFacade uniqueInstance = null;
    private static ICourseFactory courseFactory;



    private static IAssignmentFactory assignmentFactory;



    private static IQuizFactory quizFactory;


    private static IStudentFactory studentFactory;
    private static IUniversityTermsFactory universityTermsFactory;

    private FactoryFacade() {
        courseFactory = new CourseFactory();
        studentFactory = new StudentFactory();
        universityTermsFactory = new UniversityTermsFactory();
        assignmentFactory = new AssignmentFactory();
        quizFactory = new QuizFactory();
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

    public IStudentFactory getStudentFactory() {
        return studentFactory;
    }

    public IUniversityTermsFactory getUniversityTermsFactory(){
        return universityTermsFactory;
    }

    public IAssignmentFactory getAssignmentFactory() {
        return assignmentFactory;
    }

    public IQuizFactory getQuizFactory() {
        return quizFactory;
    }



}
