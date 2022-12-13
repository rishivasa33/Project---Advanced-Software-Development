package dal.csci5308.project.group15.elearning.models.deadlineNotification;

public class CourseMaterialDeadlineNotification
{
    private String subjectId;
    private String materialId;
    private String materialName;
    private String startDate;
    private String endDate;

    private String numDays;

    public CourseMaterialDeadlineNotification()
    {

    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNumDays() {
        return numDays;
    }

    public void setNumDays(String numDays) {
        this.numDays = numDays;
    }

    @Override
    public String toString() {
        return "CourseMaterialDeadlineNotification{" +
                "subjectId='" + subjectId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", numDays='" + numDays + '\'' +
                '}';
    }
}
