package com.grademanagement.grade_management_system.web;





import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.fasterxml.jackson.databind.ObjectMapper;


import com.grademanagement.grade_management_system.pojo.Course;
import com.grademanagement.grade_management_system.pojo.Student;
import com.grademanagement.grade_management_system.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;
    private Course course;

    @BeforeEach
    public void setup() {
        student = new Student();
        student.setId(1L);
        student.setName("Harry Potter");

        course = new Course();
        course.setId(1L);
        course.setSubject("Charms");
    }

    @Test
    public void testGetStudent() throws Exception {
        when(studentService.getStudent(1L)).thenReturn(student);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Harry Potter"));
    }

    @Test
    public void testSaveStudent() throws Exception {
        when(studentService.saveStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Harry Potter"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(student);
        when(studentService.getStudents()).thenReturn(students);

        mockMvc.perform(get("/student/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Harry Potter"));
    }

    @Test
    public void testGetEnrolledCourses() throws Exception {
        Set<Course> courses = new HashSet<>();
        courses.add(course);
        when(studentService.getEnrolledCourses(1L)).thenReturn(courses);

        mockMvc.perform(get("/student/1/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subject").value("Charms"));
    }
}
