package com.almundo.call_center.interfaces;

public interface DataAgent {
	
	public void processData(DataMessage dataMessage);
	public Boolean isAvailable();
	public int getAttLevel();
	public void setAvailability(Boolean availability);

}
