package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.persistence.CourseInstancePersistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CourseInstance {

    private int course_instance_id_;
    private ICourse course_;
    private Date startDate_;
    private Date endDate_;

    private String GetSemesterName(){
        if(startDate_.getMonth() == Calendar.SEPTEMBER){
            return "Fall";
        }
        else if(startDate_.getMonth() == Calendar.JANUARY){
            return "Winter";
        }
        else if(startDate_.getMonth() == Calendar.MAY){
            return "Summer";
        }
        return "Unspecified Semester";
    }

    private Date DateFromString(String date_string) throws ParseException {
        String sDate1="31/12/1998";
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        }
        catch (ParseException exception){
            return new SimpleDateFormat("dd/MM/yy").parse(sDate1);
        }
    }


    CourseInstance (ICourse course, Date startDate, Date endDate){
        course_ = course;
        startDate_ = startDate;
        endDate_ = endDate;
    }

    CourseInstance (ICourse course, String startDate, String endDate) throws ParseException{
        course_ = course;
        startDate_ =  DateFromString(startDate);
        endDate_ = DateFromString(endDate);
    }

    public ICourse GetCourse(){
        return course_;
    }

    public Date GetStartDate(){
        return startDate_;
    }

    public Date GetEndDate(){
        return  endDate_;
    }

    public String GetName(){
        int start_year = startDate_.getYear();
        return start_year +
                " " +
                GetSemesterName() +
                " " +
                GetCourse().GetCourseName();
    }

    public void Save(CourseInstancePersistence courseInstancePersistence){
        courseInstancePersistence.Save(this);
    }

    public CourseInstance Load(CourseInstancePersistence courseInstancePersistence,int courseInstanceId) throws ParseException {
        return courseInstancePersistence.Load(courseInstanceId);
    }

    public int GetCourseInstanceId() {
        return course_instance_id_;
    }

}