package com.simform.mongo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Component;

import com.simform.mongo.model.Student;
import com.simform.mongo.repository.StudentRepository;
import com.simform.mongo.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudent(String id) {
		return studentRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Student> getStudentsPaginated(int page, Optional<String> sortBy) {
			return studentRepository.findAll(PageRequest.of(page, 10, Sort.Direction.ASC, sortBy.orElse("id"))).getContent();	
	}

	@Override
	public List<Student> findByFirstName(String regexp){
		List<Student> list = studentRepository.findByFirstNameStartingWith(regexp);
		list.sort((Student s1 , Student s2) -> s1.getFirstName().compareTo(s2.getFirstName()));
		return list;
		
	}
	@Override
	public List<Student> findStudentsByAgeRange(int ageGT, int ageLT){
		List<Student> list =  studentRepository.findStudentsByAgeBetween(ageGT,ageLT);
		list.sort((Student s1 , Student s2) -> s1.getAge()-s2.getAge());
		return list;
		
	}
	
	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> saveAllStudents(List<Student> students) {
		return studentRepository.saveAll(students);
	}

	@Override
	public Student updateStudent(String id, Student student) {
		Student existingStudent = studentRepository.findById(id).orElse(null);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setAge(student.getAge());
		
		//EXTRA LOGIC
//		System.out.println(existingStudent);
//		
//		if ((student.getFirstName() != null && student.getFirstName() != ""))
//			existingStudent.setFirstName(student.getFirstName());
//		if ((student.getLastName() != null && student.getLastName() != ""))
//			existingStudent.setLastName(student.getLastName());
//		if (student.getAge() != 0)
//			existingStudent.setAge(student.getAge());
		
		return studentRepository.save(existingStudent);
	}

	@Override
	public String removeStudent(String id) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student.equals(null))
			return "Did not Find Student";
		studentRepository.deleteById(id);
		return "Student Removed with id: "+id;
	}

	
}
