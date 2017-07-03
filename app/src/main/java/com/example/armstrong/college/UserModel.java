package com.example.armstrong.college;

/**
 * Created by armstrong on 4/4/2017.
 */
import java.io.Serializable;

/**
 * Created by sotsys069 on 3/10/16.
 */

public class UserModel implements Serializable {

    private String user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String age;
    private String sex;
    private String schedule;
    private String position;
    private String course;

    private String mobile_number;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public String getPosition() {
        return position;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    public String getSchedule() {
        return schedule;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
