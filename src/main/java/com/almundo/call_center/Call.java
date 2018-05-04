package com.almundo.call_center;

import java.util.Random;

import com.almundo.call_center.interfaces.DataMessage;

public class Call implements DataMessage{

	private Integer callDuration = 0;
	private String callerName;
	private String calleeName;

	public Call(String callerName, String calleeName) {
		this.calleeName = calleeName;
		this.callerName = callerName;
		
		Random randomGenerator = new Random();
		this.callDuration = randomGenerator.nextInt(6) + 5;		
	}

	@Override
	public Integer getDataMessage() {
		return callDuration;
	}
	
	public String getCallDetails(){
		return callerName + " calling " + calleeName;
	}

	@Override
	public Object getDataInfo() {		
		return "Call " + this.callerName + "/" + this.calleeName;
	}
}
