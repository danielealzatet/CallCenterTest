package com.almundo.call_center;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.almundo.call_center.interfaces.DataAgent;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import utils.Constants;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testDispatcher(){
        List<DataAgent> callAgents = new ArrayList<DataAgent>();
        callAgents.add(new CallAgent(Constants.AGENT_LEVEL_1));
        
        CallHandler callHandler = new CallHandler(callAgents);
        
        Dispatcher dispatcher = new Dispatcher(callHandler);
        
        try{
        	List<Callable<Boolean>> calls = new ArrayList();
        	for(int i=1; i<=10;i++){
        		calls.add(() -> {
             			dispatcher.dispatchCall(new Call("GenericCaller","GenericCallee"));
             			return true;
             			});        		
        	}             
             ExecutorService executor = Executors.newFixedThreadPool(20);           
             executor.invokeAll(calls);
        }catch(Exception ex){
        	assertTrue(false);
        }       
    }
    
    
}
