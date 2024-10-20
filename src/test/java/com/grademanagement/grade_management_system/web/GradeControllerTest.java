package com.grademanagement.grade_management_system.web;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.grademanagement.grade_management_system.pojo.Grade;
import com.grademanagement.grade_management_system.service.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@WebMvcTest(GradeController.class)
public class GradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradeService gradeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Grade grade;

    @BeforeEach
    public void setup() {
        grade = new Grade();
        grade.setId(1L);
        grade.setScore("A+");
    }

    @Test
    public void testGetGrade() throws Exception {
        when(gradeService.getGrade(1L, 1L)).thenReturn(grade);

        mockMvc.perform(get("/grade/student/1/course/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value("A+"));
    }

    @Test
    public void testSaveGrade() throws Exception {
        when(gradeService.saveGrade(any(Grade.class), eq(1L), eq(1L))).thenReturn(grade);

        mockMvc.perform(post("/grade/student/1/course/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(grade)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.score").value("A+"));
    }

    @Test
    public void testUpdateGrade() throws Exception {
        when(gradeService.updateGrade(eq("A+"), eq(1L), eq(1L))).thenReturn(grade);

        mockMvc.perform(put("/grade/student/1/course/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(grade)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value("A+"));
    }

    @Test
    public void testDeleteGrade() throws Exception {
        doNothing().when(gradeService).deleteGrade(1L, 1L);

        mockMvc.perform(delete("/grade/student/1/course/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetStudentGrades() throws Exception {
        when(gradeService.getStudentGrades(1L)).thenReturn(Arrays.asList(grade));

        mockMvc.perform(get("/grade/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].score").value("A+"));
    }

    @Test
    public void testGetAllGrades() throws Exception {
        when(gradeService.getAllGrades()).thenReturn(Arrays.asList(grade));

        mockMvc.perform(get("/grade/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].score").value("A+"));
    }
}

