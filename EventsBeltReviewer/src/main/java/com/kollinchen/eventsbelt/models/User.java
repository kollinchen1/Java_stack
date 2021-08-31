package com.kollinchen.eventsbelt.models;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    // MEMBER VARIABLES
	@NotEmpty(message="Email is required!")
	@Email(message = "Must have valid email format")
    private String email;
    
	@NotEmpty(message = "Please enter your first name")
	@Size(min=2, max=128, message="At least 2 characters")
	private String first_name;
	
	@NotEmpty(message = "Please enter your last name")
	@Size(min=2, max=128, message="At least 2 characters")
	private String last_name;
	
	@NotEmpty(message = "Please enter your city")
	private String city;
    
	private String state;
	
    @NotEmpty()
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    @NotEmpty(message = "Please confirm your pw")
    @Transient
    private String passwordConfirmation;
    
    //Relationships
    @OneToMany(mappedBy="host", fetch = FetchType.LAZY)
    private List<Event> createdEvents;
    
    
    //MtoM FUNCTIONALITY FOR LEAVING MESSAGES
    @OneToMany(mappedBy="sender", fetch = FetchType.LAZY)
    private List<Message> messages;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_in_events", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;
    
    // CREATED AND UPDATED AT
    @Column(updatable=false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
    
	public User() {}

	public User(String email,
			String first_name,
			String last_name,
			String city,
			String state,
			String password,
			String passwordConfirmation) {
		super();
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.city = city;
		this.state = state;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}
	
	//gettters and setters
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public List<Event> getCreatedEvents() {
		return createdEvents;
	}

	public void setCreatedEvents(List<Event> createdEvents) {
		this.createdEvents = createdEvents;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
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