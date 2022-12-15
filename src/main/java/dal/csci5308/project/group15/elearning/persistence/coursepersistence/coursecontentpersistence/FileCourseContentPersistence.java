package dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence;

import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;

import java.sql.SQLException;

public interface FileCourseContentPersistence {

    int Save(FileCourseContent pdfFileCourseContent, int courseModuleId) throws SQLException;

    FileCourseContent Load(int fileCourseContentId) throws SQLException;
}
