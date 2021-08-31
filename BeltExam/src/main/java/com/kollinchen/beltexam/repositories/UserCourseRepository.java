package com.kollinchen.beltexam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kollinchen.beltexam.models.UserCourse;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse, Long> {
	List<UserCourse> findAll();
}
