package com.example.school_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementSystemApplication.class, args);
	}

	/*
	1. create multiple students
	2. create multiple teachers
	3. create multiple subjects associated with teacher id
	4. set students to subjects
	5. create multiple assignments associated with subject
	6. create grade and associate it to a student and an assignment
	 */

}
