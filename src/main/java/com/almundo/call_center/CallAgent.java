package com.almundo.call_center;

import com.almundo.call_center.interfaces.DataAgent;
import com.almundo.call_center.interfaces.DataMessage;

public class CallAgent implements DataAgent{

	private int attendanceLevel;
	private boolean availability;

	public CallAgent(int attendanceLevel) {
		this.attendanceLevel = attendanceLevel;
		setAvailability(true);
	}

	@Override
	public Boolean isAvailable() {
		return availability;
	}

	@Override
	public int getAttLevel() {
		return attendanceLevel;
	}

	@Override
	public void processData(DataMessage dataMessage) {
		
		Integer callDuration = (Integer)dataMessage.getDataMessage();		
		
		try{
			do{
				Thread.sleep(1000);				
				System.out.println("Thread: " + Thread.currentThread().getName() + " Agent level: " + attendanceLevel +
						" CallTime: " + callDuration +
						" processing " + ((Call)dataMessage).getCallDetails());
				callDuration--;
			}while(callDuration > 0);			
				
		}catch(InterruptedException Ex){
			System.out.println("Interrputed exception");	
		}finally{
			setAvailability(true);
		}
		
	}

	@Override
	public void setAvailability(Boolean availability) {
		this.availability = availability;		
	}
}
