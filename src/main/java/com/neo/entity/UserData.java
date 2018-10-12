package com.neo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserData {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String studentId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    public UserData() {  //无参构造

    }

    public UserData(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
