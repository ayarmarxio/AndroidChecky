package com.example.checky;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.Date;
import com.google.firebase.firestore.ServerTimestamp;

@IgnoreExtraProperties
public class Registry {

    private String teacherName;
    private String courseName;
    private Float average;
    private @ServerTimestamp Date timestamp;
    private String userId;

    public Registry(String teacherName, String courseName, Float average, String userId){
        this.teacherName = teacherName;
        this.courseName = courseName;
        this.average = average;
        this.userId = userId;
    }

    public Registry () {
    }

    public String getTeacherName(){ return teacherName;}
    public void setTeacherName(String teacherName) {this.teacherName = teacherName;}

    public String getCourseName(){ return courseName;}
    public void setCourseName(String courseName) {this.courseName = courseName;}

    public Float getAverage(){ return average;}
    public void setAverage(Float average) {this.average = average;}

    public String getUserId() { return userId;}
    public void setUserId(String userId){this.userId = this.userId;}
}