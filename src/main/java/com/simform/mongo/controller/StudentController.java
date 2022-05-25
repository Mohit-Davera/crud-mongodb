package com.simform.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simform.mongo.model.Student;
import com.simform.mongo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	//Read
	@GetMapping("/")
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();		
	}
	
	@GetMapping("/{id}")
	public Student getAllStudents(@PathVariable("id") String id){
		return studentService.getStudent(id);		
	}
	
	@GetMapping("/search/{name}")
	public List<Student> searchAllStudentsByFirstName(@PathVariable("name") String firstName){
		return studentService.findByFirstName(firstName);	
	}
	
	@GetMapping("/age/{lt}/{gt}")
	public List<Student> findByAgeRange(@PathVariable("lt") int lt,@PathVariable("gt") int gt){
		return studentService.findStudentsByAgeRange(lt, gt);	
	}
	
	@GetMapping(value = {"/page/{page}","/page/{page}/{sortBy}"})
	public List<Student> findAllProducts(
			@PathVariable Integer page,
			@PathVariable Optional<String> sortBy
			) {
		return studentService.getStudentsPaginated(page-1,sortBy);
	}
	
	@PostMapping("/student")
	public Student saveStudent(@RequestBody Student student)
	{
		return studentService.saveStudent(student);
	}
	
	@PostMapping("/")
	public List<Student> saveStudents(@RequestBody List<Student> students)
	{
		return studentService.saveAllStudents(students);
	}
	
	@PutMapping("/{id}")
	public Student updateProduct(@PathVariable("id") String id , @RequestBody Student student) {
		return studentService.updateStudent(id,student);
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable String id) {
		return studentService.removeStudent(id);
	}
	
	
}
