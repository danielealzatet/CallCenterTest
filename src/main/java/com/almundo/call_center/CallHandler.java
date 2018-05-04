package com.almundo.call_center;

import java.util.List;
import java.util.Optional;

import com.almundo.call_center.interfaces.DataAgent;
import com.almundo.call_center.interfaces.DataEventHandler;
import com.almundo.call_center.interfaces.DataMessage;

import utils.Constants;

public class CallHandler implements DataEventHandler{

	private List<DataAgent> dataAgents; 

	public CallHandler(List<DataAgent> dataAgents) {
		this.dataAgents = dataAgents;
	}

	@Override
	public void handle(DataMessage dataMessage) throws Exception{
		
		dataAgents.stream().forEach(dA -> System.out.println(" level: " + dA.getAttLevel() + " available " + dA.isAvailable()));
		
		if(pickAgent(Constants.AGENT_LEVEL_1,dataMessage)){
			return;
		}else if(pickAgent(Constants.AGENT_LEVEL_2,dataMessage)){
			return;
		}else if(pickAgent(Constants.AGENT_LEVEL_3,dataMessage)){
			return;
		}else{
			throw (new AllAgentsBusy("All agents are busy right now"));
		}
	}
	
	private Boolean pickAgent(Integer attLevel, DataMessage dataMessage){
		
		Optional<DataAgent> result = dataAgents.stream()
				.filter(dA -> (dA.getAttLevel() == attLevel && dA.isAvailable()))
				.findAny();
			
		if(result.isPresent()){
			DataAgent agent = result.get();
			agent.setAvailability(false);
			Runnable task = () -> {
				agent.processData(dataMessage);	
			};
			Thread thread = new Thread(task);
			thread.start();			
			return true;
		}
		
		return false;
	}
}
