package dal.csci5308.project.group15.elearning.models.course.courseContent;

import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.CourseContentPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.FileCourseContentPersistence;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class FileCourseContent extends CourseContent {
    private String filePath;
    private FileCourseContentPersistence fileCourseContentPersistence;
    FileCourseContent(String heading, String filePath){
        super(heading);
        this.filePath = filePath;
        fileCourseContentPersistence = CourseContentPersistenceSingleton.GetFileCourseContentPersistence();
    }

    FileCourseContent(int contentId, String heading, String filePath){
        super(heading);
        SetContentId(contentId);
        this.filePath = filePath;
        fileCourseContentPersistence = CourseContentPersistenceSingleton.GetFileCourseContentPersistence();
    }

    public String GetFilePath(){
        return filePath;
    }

    public void Save(int courseModuleId) throws SQLException {
       int id =  fileCourseContentPersistence.Save(this, courseModuleId);
       SetContentId(id);
    }

    public CourseContent Load(int courseContentID) throws SQLException {
        CourseContent courseContent =  fileCourseContentPersistence.Load(courseContentID);
        courseContent.SetContentId(courseContentID);
        return courseContent;
    }

    public String GetFileName(){
        Path path = Paths.get(filePath);
        Path fileName = path.getFileName();
        String fileNameString = fileName.toString();
        return fileNameString.split("\\.", 2)[1].trim();
    }
}
