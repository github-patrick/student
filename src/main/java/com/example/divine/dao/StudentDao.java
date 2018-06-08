package com.example.divine.dao;

import com.example.divine.model.Student;
import org.springframework.data.repository.CrudRepository;


public interface StudentDao extends CrudRepository<Student, Long> {
}
