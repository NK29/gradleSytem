package com.grademanagement.grade_management_system.repository;

import com.grademanagement.grade_management_system.pojo.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

}