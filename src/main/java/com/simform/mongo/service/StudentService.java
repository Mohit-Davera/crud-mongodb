package com.simform.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.simform.mongo.model.Student;

@Service
public interface StudentService {

	//GET
	public List<Student> getAllStudents();
	
	public Student getStudent(String id);
	
	public List<Student> getStudentsPaginated(int page, Optional<String> sortBy);
	
	public List<Student> findByFirstName(String firstName);
	
	public List<Student> findStudentsByAgeRange(int ageGT, int ageLT);
	
	//POST
	public Student saveStudent(Student student);
	
	public List<Student> saveAllStudents(List<Student> students);
	
	//UPDATE
	public Student updateStudent(String id,Student student);
	
	//DELETE
	public String removeStudent(String id);
}
