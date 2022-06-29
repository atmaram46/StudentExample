package com.student.service;

import com.student.entity.StudentInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StudentMenu {

    public static void main(String[] args) {
        StudentMenu sm = new StudentMenu();
        Student student = new Student();
        sm.generateMenuOptions();
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice = sm.generateChoice(reader);
        while(choice != 7) {
            sm.performChoice(choice, reader, student);
            sm.generateMenuOptions();
            choice = sm.generateChoice(reader);
        }
        System.out.println("Exiting the Options!!!");
    }

    public void performChoice(int choice, BufferedReader reader, Student student) {
        if(choice == 1) {
            performChoiceOne(reader, student);
        } else if(choice == 2) {
            performChoiceTwo(student);
        } else if(choice == 3) {
            performChoiceThree(reader, student);
        } else if(choice == 4) {
            performChoiceFours(reader, student);
        } else if(choice == 5) {
            performChoiceFive(reader, student);
        } else if(choice == 6) {
            performChoiceSix(reader, student);
        }
    }

    private void performChoiceTwo(Student student) {
        List<StudentInfo> result = student.returnAll();
        result.stream().forEachOrdered(System.out::println);
    }

    private void performChoiceSix(BufferedReader reader, Student student) {
        System.out.println("Enter Id to Delete:");
        student.removeStudent(student.searchById(generateChoice(reader)));
    }

    private void performChoiceFive(BufferedReader reader, Student student) {
        StudentInfo newStudentData = new StudentInfo();
        System.out.println("Enter Id to Search:");
        newStudentData.setId(generateChoice(reader));
        System.out.println("Enter New GPA:");
        newStudentData.setGpa(generateGPA(reader));
        System.out.println(student.updateStudentInfo(newStudentData));
    }

    private void performChoiceFours(BufferedReader reader, Student student) {
        System.out.println("Enter Last Name to Search:");
        String lName = generateNames(reader);
        System.out.println(student.searchByLastName(lName));
    }

    private void performChoiceThree(BufferedReader reader, Student student) {
        System.out.println("Enter Id to Search:");
        int id = generateChoice(reader);
        System.out.println(student.searchById(id));
    }

    private void performChoiceOne(BufferedReader reader, Student student) {
        StudentInfo studentInfo = acceptStudentData(reader);
        System.out.println(student.addNewStudent(studentInfo));
    }

    private StudentInfo acceptStudentData(BufferedReader reader) {
        StudentInfo studentInfo = new StudentInfo();
        System.out.println("Enter First Name:");
        studentInfo.setFirstName(generateNames(reader));
        System.out.println("Enter Last Name:");
        studentInfo.setLastName(generateNames(reader));
        System.out.println("Enter GPA:");
        studentInfo.setGpa(generateGPA(reader));
        System.out.println("Enter Id:");
        studentInfo.setId(generateChoice(reader));
        return studentInfo;
    }

    private double generateGPA(BufferedReader reader) {
        // Reading data using readLine
        double data = 0.0;
        try {
            data = Double.parseDouble(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error while Reading the data from Scanner!!!");
        }
        return data;
    }

    public String generateNames(BufferedReader reader) {
        // Reading data using readLine
        String data = null;
        try {
            data = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error while Reading the data from Scanner!!!");
        }
        return data;
    }

    public int generateChoice(BufferedReader reader) {
        // Reading data using readLine
        int choice = 0;
        try {
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error while Reading the data from Scanner!!!");
        }
        return choice;
    }

    public void generateMenuOptions() {
        System.out.println("Student Menu: \n" +
                "1 - Add new student \n" +
                "2 - Display all students \n" +
                "3 - Search for student (by id) \n" +
                "4 - Search for student (by last name) \n" +
                "5 - Modify a student’s gpa \n" +
                "6 - Remove student (by id) \n" +
                "7 - Exit \n" +
                "Please enter your choice: ");
    }
}
