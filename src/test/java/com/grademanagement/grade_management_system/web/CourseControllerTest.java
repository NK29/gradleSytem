package com.grademanagement.grade_management_system.web;





import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.grademanagement.grade_management_system.pojo.Course;
import com.grademanagement.grade_management_system.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    private Course course;

    @BeforeEach
    public void setup() {
        course = new Course();
        course.setId(1L);
        course.setSubject("Charms");
        course.setCode("CH104");
    }

    @Test
    public void testGetCourse() throws Exception {
        when(courseService.getCourse(1L)).thenReturn(course);

        mockMvc.perform(get("/course/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value("Charms"));
    }

    @Test
    public void testSaveCourse() throws Exception {
        when(courseService.saveCourse(any(Course.class))).thenReturn(course);

        mockMvc.perform(post("/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subject").value("Charms"));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourse(1L);

        mockMvc.perform(delete("/course/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllCourses() throws Exception {
        when(courseService.getCourses()).thenReturn(Arrays.asList(course));

        mockMvc.perform(get("/course/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subject").value("Charms"));
    }

    @Test
    public void testEnrollStudentToCourse() throws Exception {
        when(courseService.addStudentToCourse(1L, 1L)).thenReturn(course);

        mockMvc.perform(put("/course/1/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value("Charms"));
    }

    @Test
    public void testGetEnrolledStudents() throws Exception {
        mockMvc.perform(get("/course/1/students"))
                .andExpect(status().isOk());
    }
}
