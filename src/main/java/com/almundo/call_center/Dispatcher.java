package com.almundo.call_center;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.almundo.call_center.interfaces.DataMessage;

import utils.Constants;

public class Dispatcher implements Runnable{

	private ArrayBlockingQueue<DataMessage> queue = new ArrayBlockingQueue<DataMessage>(Constants.MAX_CALLS);
	private CallHandler callHandler;

	public Dispatcher(CallHandler callHandler){		
		this.callHandler = callHandler;		
		new Thread(this).start();  		
	}
	
	public void dispatchCall(DataMessage dataMessage) {
		try{
			queue.put(dataMessage);	
		}catch(InterruptedException ex){
			System.out.println(ex);
		}
		
		System.out.println("Adding to queue: " + Thread.currentThread().getName());
	}

	@Override
	public void run() {
		DataMessage dataMessage = null;
	    while (true) {		    	
	    	try{	    		
	    		dataMessage = queue.take();
	    		System.out.println(" cola " + queue.size());
	    		callHandler.handle(dataMessage);	
	    	}catch(AllAgentsBusy ex1){
	    		try{
		    		queue.put(dataMessage);    		
		    		System.out.println("All agents busy. Message: " + dataMessage.getDataInfo() + " waiting ");
	    			Thread.sleep(3000);	
	    		}catch(Exception ex_){
	    			System.out.println(ex_);
	    		}
	    			
	    	}catch(Exception ex2){
	    		System.out.println(ex2);
	    	}    						    		
	    }
		
	}	
}
