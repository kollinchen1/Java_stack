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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 1, message = "Event name should be at least 1" )
	private String name;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date host_date;
	
	@NotEmpty(message = "Please enter your city")
	private String city;
    
	private String state;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_in_events", 
        joinColumns = @JoinColumn(name = "event_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> joinedUsers;
	
	// RELATIONSHIPS
	//MtoO FUNCTIONALITY FOR CREATOR OF THIS COMMENT
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") // FOREIGN KEY NAME
	private User host;
	
	//MtoM FUNCTIONALITY FOR LEAVING MESSAGES
    @OneToMany(mappedBy="event", fetch = FetchType.LAZY)
    private List<Message> messages;
    
    @Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
    public Event() {}

	public Event(String name,
			Date host_date,
			String city, 
			String state) {
		super();
		this.name = name;
		this.host_date = host_date;
		this.city = city;
		this.state = state;
	}
	//getters setters
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

	public Date getHost_date() {
		return host_date;
	}

	public void setHost_date(Date host_date) {
		this.host_date = host_date;
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

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
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

	public List<User> getJoinedUsers() {
		return joinedUsers;
	}

	public void setJoinedUsers(List<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
    
}
