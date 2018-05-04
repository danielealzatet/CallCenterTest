package com.almundo.call_center.interfaces;

/**
 * @author daniel
 * The idea behind this interface is to have agents that could process calls, or diferent types of
 * Data messages
 *
 */
public interface DataAgent {
	
	public void processData(DataMessage dataMessage);
	public Boolean isAvailable();
	public int getAttLevel();
	public void setAvailability(Boolean availability);

}
