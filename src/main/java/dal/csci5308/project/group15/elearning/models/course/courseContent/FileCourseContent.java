package dal.csci5308.project.group15.elearning.models.course.courseContent;

import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.CourseContentPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.FileCourseContentPersistence;
import org.springframework.security.core.parameters.P;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class FileCourseContent extends CourseContent {
    private String filePath;
    private FileCourseContentPersistence fileCourseContentPersistence;

    private IFileCourseContentFileState fileCourseContentFileState;

    private  String GetFileType(String filePath){
        String extension = "";

        int i = filePath.lastIndexOf('.');
        if (i > 0) {
            extension = filePath.substring(i+1);
        }
        return extension.toLowerCase();
    }
    FileCourseContent(String heading, String filePath){
        super(heading);
        this.filePath = filePath;
        fileCourseContentPersistence = CourseContentPersistenceSingleton.GetFileCourseContentPersistence();
        String fileType = GetFileType(filePath);
        switch (fileType) {
            case "pdf" -> {
                fileCourseContentFileState = new FileCourseContentPdfFileState(this);
                break;
            }
            case "jpg", "png", "jpeg" -> {
                fileCourseContentFileState = new FileCourseContentImageFileState(this);
                break;
            }
            default -> {
                fileCourseContentFileState = new FileCourseContentInvalidFileState(this);
                break;
            }
        }
    }

    void SetFileContentState(IFileCourseContentFileState state){
        fileCourseContentFileState = state;
    }

    public IFileCourseContentFileState GetState(){
        return fileCourseContentFileState;
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
        return fileName.toString();
    }
}
