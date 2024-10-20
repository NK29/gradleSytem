package com.grademanagement.grade_management_system.service;

import com.grademanagement.grade_management_system.pojo.Course;
import com.grademanagement.grade_management_system.pojo.Student;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    Course addStudentToCourse(Long studentId, Long courseId);
    List<Course> getCourses();
    Set<Student> getEnrolledStudents(Long id);
}
