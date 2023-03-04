package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Students;
import com.example.demo.repository.StudentsRepository;

@Service
public class StudentsService {

	@Autowired
	StudentsRepository studentsRepository;

	public void save(Students students) {
		studentsRepository.save(students);

	}

}
