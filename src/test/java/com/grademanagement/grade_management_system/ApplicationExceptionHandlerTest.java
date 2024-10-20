/*
package com.grademanagement.grade_management_system;




import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.grademanagement.grade_management_system.exception.CourseNotFoundException;
import com.grademanagement.grade_management_system.exception.ErrorResponse;
import com.grademanagement.grade_management_system.exception.GradeNotFoundException;
import com.grademanagement.grade_management_system.exception.StudentNotEnrolledException;
import com.grademanagement.grade_management_system.exception.StudentNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;

public class ApplicationExceptionHandlerTest {

    private final ApplicationExceptionHandler exceptionHandler = new ApplicationExceptionHandler();

    @Test
    public void testHandleResourceNotFoundException() {
        RuntimeException ex = new CourseNotFoundException("Course not found");

        ResponseEntity<Object> response = exceptionHandler.handleResourceNotFoundException(ex);

        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
        assert errorResponse.getErrors().contains("Course not found");
    }

    @Test
    public void testHandleEmptyResultDataAccessException() {
        EmptyResultDataAccessException ex = new EmptyResultDataAccessException("Cannot delete non-existing resource", 1);

        ResponseEntity<Object> response = exceptionHandler.handleDataAccessException(ex);

        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
        assert errorResponse.getErrors().contains("Cannot delete non-existing resource");
    }

    @Test
    public void testHandleDataIntegrityViolationException() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Data Integrity Violation");

        ResponseEntity<Object> response = exceptionHandler.handleDataIntegrityViolationException(ex);

        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
        assert errorResponse.getErrors().contains("Data Integrity Violation: we cannot process your request.");
    }

    @Test
    public void testHandleMethodArgumentNotValid() {
        // Arrange
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        WebRequest request = mock(WebRequest.class);

        // Add error messages to the mock exception
        when(ex.getBindingResult()).thenReturn(new MockBindingResult());

        // Act
        ResponseEntity<Object> response = exceptionHandler.handleMethodArgumentNotValid(ex, headers, status, request);

        // Assert
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
        assert errorResponse.getErrors().size() > 0; // Change this as needed based on your error setup
    }

    // Mock BindingResult class for the purpose of this test
    private static class MockBindingResult extends org.springframework.validation.BindingResult {
        @Override
        public List<ObjectError> getAllErrors() {
            return Arrays.asList(new ObjectError("field", "Field error message"));
        }

        // Implement other abstract methods if needed
    }
}
*/
