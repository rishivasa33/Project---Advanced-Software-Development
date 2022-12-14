package dal.csci5308.project.group15.elearning.models.course.courseContent;

public class FileCourseContentInvalidFileState implements  IFileCourseContentFileState{

    FileCourseContent fileCourseContent;

    FileCourseContentInvalidFileState(FileCourseContent fileCourseContent){
        this.fileCourseContent = fileCourseContent;
    }

    public String GetFileType(){
        return "INVALID";
    }
    public boolean IsFileValid(){
        return false;
    }
}
