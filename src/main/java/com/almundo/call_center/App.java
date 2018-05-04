package com.almundo.call_center;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.almundo.call_center.interfaces.DataAgent;
import com.almundo.call_center.interfaces.DataMessage;

import utils.Constants;


public class App 
{
    public static void main( String[] args )
    {
               
        
        List<DataAgent> callAgents = new ArrayList<DataAgent>();
        callAgents.add(new CallAgent(Constants.AGENT_LEVEL_1));
        callAgents.add(new CallAgent(Constants.AGENT_LEVEL_1));
        callAgents.add(new CallAgent(Constants.AGENT_LEVEL_1));
        callAgents.add(new CallAgent(Constants.AGENT_LEVEL_2));
        callAgents.add(new CallAgent(Constants.AGENT_LEVEL_2));
        callAgents.add(new CallAgent(Constants.AGENT_LEVEL_3));
        
        CallHandler callHandler = new CallHandler(callAgents);
        
        Dispatcher dispatcher = new Dispatcher(callHandler);
        
        List<Callable<Boolean>> callables = Arrays.asList(
        		() -> {
        			System.out.println("Attempting to dispatch Call1");
        			dispatcher.dispatchCall(new Call("Caller1","Callee1"));
        			System.out.println("Call1 dispatched");
        			return true;
        			},
        		() -> {
        			System.out.println("Attempting to dispatch Call2");
        			dispatcher.dispatchCall(new Call("Caller2","Callee2"));
        			System.out.println("Call2 dispatched");
        			return true;
        			},
        		() -> {
        			System.out.println("Attempting to dispatch Call3");
        			dispatcher.dispatchCall(new Call("Caller3","Callee3"));
        			System.out.println("Call3 dispatched");
        			return true;
        			},
        		() -> {
        			System.out.println("Attempting to dispatch Call4");
        			dispatcher.dispatchCall(new Call("Caller4","Callee4"));
        			System.out.println("Call4 dispatched");
        			return true;
        			},
        		() -> {
        			System.out.println("Attempting to dispatch Call5");
        			dispatcher.dispatchCall(new Call("Caller5","Callee5"));
        			System.out.println("Call5 dispatched");
        			return true;
        			},
        		() -> {
        			System.out.println("Attempting to dispatch Call6");
        			dispatcher.dispatchCall(new Call("Caller6","Callee6"));
        			System.out.println("Call6 dispatched");
        			return true;
        			},
        		() -> {
        			System.out.println("Attempting to dispatch Call7");
        			dispatcher.dispatchCall(new Call("Caller7","Callee7"));
        			System.out.println("Call7 dispatched");
        			return true;
        			},
        		() -> {
        			System.out.println("Attempting to dispatch Call8");
        			dispatcher.dispatchCall(new Call("Caller8","Callee8"));
        			System.out.println("Call8 dispatched");
        			return true;
        			},
        		() -> {
        			System.out.println("Attempting to dispatch Call9");
        			dispatcher.dispatchCall(new Call("Caller9","Callee9"));
        			System.out.println("Call9 dispatched");
        			return true;
        			},
        		() -> {
        			System.out.println("Attempting to dispatch Call10");
        			dispatcher.dispatchCall(new Call("Caller10","Callee10"));
        			System.out.println("Call10 dispatched");
        			return true;
        			}
        		);
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        try{
        	executor.invokeAll(callables);	
        }catch(Exception ex){
        	System.out.println(ex);
        }
    }
    
    public static Callable<Call> callable(String caller, String callee) {
        return () -> {
            return new Call(caller, callee);
        };
    }
}
