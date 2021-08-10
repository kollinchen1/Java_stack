package com.kollinchen.ninjagoldgame.services;

public class Message {
	private String message;
	private int gain;
	
	public Message(String message,int gain) {
		super();
		this.message = message;
		this.gain = gain;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getGain() {
		return gain;
	}

	public void setGain(int gain) {
		this.gain = gain;
	}
}
