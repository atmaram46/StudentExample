package com.student.service;

import com.student.entity.StudentInfo;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final List<StudentInfo> studentList;

    public Student() {
        studentList = new ArrayList<StudentInfo>();
    }

    public List<StudentInfo> returnAll() {
        return studentList;
    }

    public String addNewStudent(StudentInfo studentInfo) {
        StudentInfo addStudent = searchById(studentInfo.getId());
        if(addStudent == null)
            studentList.add(studentInfo);
        else
            return "Error: id already exists";
        return "Success";
    }

    public String updateStudentInfo(StudentInfo studentInfo) {
        StudentInfo addStudent = searchById(studentInfo.getId());
        if(addStudent == null)
            return "Error: id doesn't exists";
        else {
            removeStudent(addStudent);
            addStudent.setGpa(studentInfo.getGpa());
            studentList.add(addStudent);
        }
        return "Success";
    }

    public void removeStudentByID(int id) {
        studentList.remove(searchById(id));
    }

    public void removeStudent(StudentInfo studentInfo) {
        studentList.remove(studentInfo);
    }

    public StudentInfo searchById(int id) {
        return studentList.stream().filter(studentInfo -> id == (studentInfo.getId()))
                .findAny()
                .orElse(null);
    }

    public StudentInfo searchByLastName(String lName) {
        return studentList.stream().filter(studentInfo -> lName.equals(studentInfo.getLastName()))
                .findAny()
                .orElse(null);
    }
}
