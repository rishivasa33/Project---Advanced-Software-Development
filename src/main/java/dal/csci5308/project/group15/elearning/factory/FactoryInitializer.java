package dal.csci5308.project.group15.elearning.factory;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;

public class FactoryInitializer {

    static FactoryInitializer uniqueInstance = null;
    private static ICourseFactory courseFactory;

    private FactoryInitializer() {
        courseFactory = new CourseFactory();
    }

    public static FactoryInitializer instance() {
        if (null == uniqueInstance) {
            System.out.println("Creating NEW single instance of FactoryInitializer");

            uniqueInstance = new FactoryInitializer();
            return uniqueInstance;
        }
        return uniqueInstance;
    }

    public ICourseFactory getCourseFactory(){
        return courseFactory;
    }

}
