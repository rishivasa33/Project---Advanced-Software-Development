package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;

public interface CourseModulePersistence {

    int Save(CourseModule courseModule);

    CourseModule Load(int courseModuleID);


}
