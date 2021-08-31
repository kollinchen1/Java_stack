package com.kollinchen.beltexam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kollinchen.beltexam.models.Course;
import com.kollinchen.beltexam.repositories.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepo;

	// CREATE & UPDATE
	public Course saveCourse(Course Course) {
		return courseRepo.save(Course);
	}

	// FIND ALL Courses
	public List<Course> allCourses() {
		return courseRepo.findAll();
	}

	// FIND ONE Course
	public Course findOneCourse(Long id) {
		return courseRepo.findById(id).orElse(null);
	}
	// UPDATE
	public Course updateCourse(Course Course) {
		return courseRepo.save(Course);
	 }
	// DELETE AN Course
	public void deleteCourse(Long id) {
		courseRepo.deleteById(id);
	}
}
