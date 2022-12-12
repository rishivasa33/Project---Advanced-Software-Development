package dal.csci5308.project.group15.elearning.models.course.courseContent;

public class PdfFileCourseContent extends CourseContent {
    private String filePath;
    PdfFileCourseContent(String heading, String filePath){
        super(heading);
        this.filePath = filePath;
    }

    public String GetPdfFilePath(){
        return filePath;
    }

    public void Save(int courseModuleId){

    }

    public CourseContent Load(int courseContentID){
        return null;
    }
}
