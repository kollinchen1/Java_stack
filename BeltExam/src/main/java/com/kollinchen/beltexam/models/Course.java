package com.kollinchen.beltexam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="courses")
public class Course {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message = "Please enter course name")
	private String name;
	
	@NotEmpty(message = "Please enter instuctor name")
	private String instructor;
	
	@NotNull
    @Min(0)
	private Integer capacity;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_in_courses", 
        joinColumns = @JoinColumn(name = "course_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> joinedUsers;
//	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
//	private List<UserCourse> members;
	
    // CREATED AND UPDATED AT
    @Column(updatable=false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	public Course() {}

	public Course(String name,
			String instructor, 
			Integer capacity) {
		super();
		this.name = name;
		this.instructor = instructor;
		this.capacity = capacity;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

//	public List<UserCourse> getMembers() {
//		return members;
//	}
//
//	public void setMembers(List<UserCourse> members) {
//		this.members = members;
//	}

	public List<User> getJoinedUsers() {
		return joinedUsers;
	}

	public void setJoinedUsers(List<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
