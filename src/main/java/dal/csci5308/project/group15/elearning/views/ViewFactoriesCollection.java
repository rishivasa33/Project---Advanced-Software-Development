package dal.csci5308.project.group15.elearning.views;

import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentViewFactory;

public class ViewFactoriesCollection {

    private  static  CourseContentViewFactory courseContentViewFactoryInstance;

    private static ViewFactoriesCollection viewFactoriesCollectionInstance = null;


    private ViewFactoriesCollection(){
        courseContentViewFactoryInstance = new CourseContentViewFactory();
    }

    static void initialize(){
        if(viewFactoriesCollectionInstance == null){
           viewFactoriesCollectionInstance = new ViewFactoriesCollection();
        }
    }

    public  static CourseContentViewFactory GetCourseContentViewFactory(){
        initialize();
        return courseContentViewFactoryInstance;
    }
}
