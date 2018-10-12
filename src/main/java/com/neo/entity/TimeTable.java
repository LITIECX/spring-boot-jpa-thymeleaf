package com.neo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TimeTable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String studentId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String course;
    @Column(nullable = false)
    private String teacher;
    @Column(nullable = false)
    private int starWeek;
    @Column(nullable = false)
    private int endWeek;
    @Column(nullable = false)
    private int oneTow;  //单双周 1为单周，2为双周
    @Column(nullable = false)
    private String classRoom;
     //课程定位坐标 X:week ;Y:section ,sectionSpan
    @Column(nullable = false)
    private int week; //周几
    @Column(nullable = false)
    private int section; //从第几节课开始
    @Column(nullable = false)
    private int sectionSpan; //跨几节课

    public TimeTable() {
    }

    public TimeTable(String studentId, String name, String course, String teacher, int starWeek, int endWeek, int oneTow,
                     String classRoom, int week, int section, int sectionSpan) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.teacher = teacher;
        this.starWeek = starWeek;
        this.endWeek = endWeek;
        this.oneTow = oneTow;
        this.classRoom = classRoom;
        this.week = week;
        this.section = section;
        this.sectionSpan = sectionSpan;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getStarWeek() {
        return starWeek;
    }

    public void setStarWeek(int starWeek) {
        this.starWeek = starWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getOneTow() {
        return oneTow;
    }

    public void setOneTow(int oneTow) {
        this.oneTow = oneTow;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getSectionSpan() {
        return sectionSpan;
    }

    public void setSectionSpan(int sectionSpan) {
        this.sectionSpan = sectionSpan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
