package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.persistence.TextCourseContentPersistence;

public class MockDBCourseTextCourseContentPersistence implements TextCourseContentPersistence{

    public int Save(CourseContent courseContent){
       return 1;
    }

    public CourseContent Load(int courseContentId){
        CourseContentFactory courseContentFactory = new CourseContentFactory();
        return courseContentFactory.CreateTextCourseContent ("content heading", "content text");
    }
}
