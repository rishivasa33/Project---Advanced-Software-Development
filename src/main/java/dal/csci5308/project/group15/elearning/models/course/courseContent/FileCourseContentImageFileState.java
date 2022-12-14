package dal.csci5308.project.group15.elearning.models.course.courseContent;

public class FileCourseContentImageFileState implements IFileCourseContentFileState{

    FileCourseContent fileCourseContent;

    FileCourseContentImageFileState (FileCourseContent fileCourseContent){
        this.fileCourseContent = fileCourseContent;
    }

    public String GetFileType(){
        return "IMAGE";
    }
    public boolean IsFileValid(){
        return true;
    }
}
