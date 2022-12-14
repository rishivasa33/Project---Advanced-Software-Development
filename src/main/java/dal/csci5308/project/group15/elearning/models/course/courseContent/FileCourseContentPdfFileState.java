package dal.csci5308.project.group15.elearning.models.course.courseContent;

public class FileCourseContentPdfFileState implements IFileCourseContentFileState {


    FileCourseContent fileCourseContent;

    FileCourseContentPdfFileState(FileCourseContent fileCourseContent){
        this.fileCourseContent = fileCourseContent;
    }

    public String GetFileType(){
        return "PDF";
    }
    public boolean IsFileValid(){
        return true;
    }
}
