package com.weekone.phone;

public class Galaxy extends Phone implements Ringable {
    public Galaxy(String versionNumber, int batteryPercentage, String carrier, String ringTone) {
        super(versionNumber, batteryPercentage, carrier, ringTone);
    }
    @Override
    public String ring() {
    	return getClass().getSimpleName()+" "+this.getVersionNumber()+" says "+this.getRingtone();
        // your code here
    }
    @Override
    public String unlock() {
    	return "Unlocking via finger print";
        // your code here
    }
    @Override
    public void displayInfo() {
    	System.out.println(getClass().getSimpleName()+" "+this.getVersionNumber()+" from "+this.getCarrier());
        // your code here            
    }
}

