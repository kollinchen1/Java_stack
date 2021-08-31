package com.kollinchen.eventsbelt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kollinchen.eventsbelt.models.Event;
import com.kollinchen.eventsbelt.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepo;

	// CREATE & UPDATE
	public Event saveEvent(Event event) {
		return eventRepo.save(event);
	}

	// FIND ALL Events
	public List<Event> allEvents() {
		return eventRepo.findAll();
	}

	// FIND ONE Event
	public Event findOneEvent(Long id) {
		return eventRepo.findById(id).orElse(null);
	}
	// UPDATE
	public Event updateEvent(Event event) {
		return eventRepo.save(event);
	 }
	// DELETE AN Event
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}
}
