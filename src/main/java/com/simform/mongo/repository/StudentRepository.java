package com.simform.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.simform.mongo.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

	List<Student> findByFirstNameStartingWith(String firstName);
	
	@Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
	List<Student> findStudentsByAgeBetween(int ageGT, int ageLT);
}
