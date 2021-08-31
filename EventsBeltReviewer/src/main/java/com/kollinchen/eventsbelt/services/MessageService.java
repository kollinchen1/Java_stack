package com.kollinchen.eventsbelt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kollinchen.eventsbelt.models.Message;
import com.kollinchen.eventsbelt.repositories.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepo;
	
	public Message saveMessage( Message m) {
		return messageRepo.save(m);
	}

	public Message findMessage(Long Message_id) {
		return messageRepo.findById(Message_id).orElse(null);
	}

	public void deleteMessage(Long Message_id) {
		messageRepo.deleteById(Message_id);
	}
}
