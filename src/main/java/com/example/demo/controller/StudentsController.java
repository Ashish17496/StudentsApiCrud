package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Students;
import com.example.demo.repository.StudentsRepository;
import com.example.demo.service.StudentsService;

@RestController
@RequestMapping("/api")
public class StudentsController {

	@Autowired
	StudentsRepository studentsRepository;

	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@PostMapping("/students")
	public String createNewStudents(@RequestBody Students students) {
		studentsRepository.save(students);
		return "Students created in database- " + students.getName();
	}
	
	@GetMapping("/studentsread")
	public ResponseEntity<List<Students>> getAllStudents(){
		List<Students> stuList=new ArrayList<>();
		studentsRepository.findAll().forEach(stuList::add);
		return new ResponseEntity<List<Students>>(stuList,HttpStatus.OK);
		
	}
	
	@GetMapping("/studentsread/{id}")
	public ResponseEntity<Students> getStudentsById(@PathVariable long id){
		Optional<Students> stu=studentsRepository.findById((int) id);
		if(stu.isPresent()) {
				return new ResponseEntity<Students>(stu.get(),HttpStatus.FOUND);
				
		}else {
			return new ResponseEntity<Students>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PutMapping("/studentsupdate/{id}")
	public String updateStudentsById(@PathVariable long id,@RequestBody Students students) {
		Optional<Students> stu=studentsRepository.findById((int) id);
		if(stu.isPresent()) {
			Students existstu=stu.get();
			existstu.setName(students.getName());
			existstu.setAge(students.getAge());
			existstu.setAddress(students.getAddress());
			
			studentsRepository.save(existstu);
			return "Students details against ID" + id +" updated";
		
		}
		else {
				return "Students details not exist for stu id "+id;
		}
		}
	
	@DeleteMapping("/studentsdelete/{id}")
	public String deleteStudentsById(@PathVariable Integer id) {
		studentsRepository.deleteById(id);
		return"students data deleted successfully";
		
	}
	@DeleteMapping("/studentsdeleteAll")
	public String deleteAllStudents() {
		studentsRepository.deleteAll();
		return "ALL Students data deleted successfully";
	}
		
	}
	
