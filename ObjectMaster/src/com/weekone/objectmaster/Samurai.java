package com.weekone.objectmaster;

public class Samurai extends Human{
	public static int numOfSam = 0;
	public Samurai() {
		this.health = 200;
		numOfSam++;
	}
	public void deathBlow(Human h) {
		h.health = 0;
		this.health /=2;
		
	}
	public void meditate() {
		health += health/2;
	}
	public static int totalSam() {
		return numOfSam;
	}

}
