package com.grademanagement.grade_management_system.service;

import com.grademanagement.grade_management_system.pojo.Course;
import com.grademanagement.grade_management_system.pojo.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();
    Set<Course> getEnrolledCourses(Long id);
}
