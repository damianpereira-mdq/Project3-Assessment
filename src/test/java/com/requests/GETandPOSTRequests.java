package com.requests;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETandPOSTRequests {	
	
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		logger=Logger.getLogger("log4jConsole");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

	
	

    @Test
    public void APIGetRequest () {
    	
    	logger.info("---- GET Request ----");
    	
    	//specify baseUrI
    	
        RestAssured.baseURI="https://simple-books-api.glitch.me";
       		
       	//Request object

        RequestSpecification httpRequest=RestAssured.given();       		
       		
       	//Response object
       		
        Response response=httpRequest.request(Method.GET,"/books");
       		
       	//print response in console window
       		
       	String responseBody=response.getBody().asString();
       		
       	//System.out.println("Response Body is:" +responseBody);
       	logger.info("---- The response body is: "+responseBody);
       	
       		
       	//status code validation
       	int statusCode=response.getStatusCode();
       	//System.out.println("Status code is :"+statusCode);
       	logger.info("---- The status code is : "+statusCode);
       	
       	Assert.assertEquals(200,statusCode);
    }
    
    @Test
    public void APIPostRequest () {
    	
    	logger.info("---- POST Request ----");
    	
    	//specify baseUrI
    	
        RestAssured.baseURI="https://reqres.in";
       		
       	//Request object

        RequestSpecification httpRequest=RestAssured.given();  
        
        //Request JSON
        
        JSONObject requestParams=new JSONObject();
      	requestParams.put("name","Damian");
      	requestParams.put("job","QA Tester");
      		
      	httpRequest.header("Content-Type", "application/json");
      	httpRequest.body(requestParams.toString());

      	//Response object
      		
      	Response response=httpRequest.request(Method.POST,"/api/users");
      		
      	//Response in console 	
      	String responseBody=response.getBody().asString();
      		
      	//System.out.println("Response Body is:" +responseBody);
      	logger.info("---- The response Body is : "+responseBody);
      	
      		
      	//status code validation 
      		
      	int statusCode=response.getStatusCode();
      		
      	//System.out.println("Status code is:" +statusCode);
      	logger.info("---- The status code is : "+statusCode);
      	Assert.assertEquals(201,statusCode);

    	
    	
    }
    
  

	
	

}
