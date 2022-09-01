package io.javabrains.springbootstarter.client;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;


public class TopicClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
            
		 final Logger log=LoggerFactory.getLogger(TopicClient.class);

		
		 RestTemplate restTemplate =new RestTemplate();
		String getTopicId="two";
		//get client Topic based on id
		getClientTopics(restTemplate,log);
		//getClientTopicById(getTopicId,restTemplate,log);
		//ClientTopic ClientTopic1= new ClientTopic("six","cloudssssnesss","VMWare");
		//addTopic(ClientTopic1,restTemplate,log);
		
		//String updateTopicById = "six";
		//updateTopic(ClientTopic1,restTemplate,log,updateTopicById);
		String deleteTopicId="nine";
		deleteTopic(deleteTopicId,restTemplate,log);
		 
		 
		 //List<ClientTopic> listOfClientTopics = new ArrayList<ClientTopic>();
		 //ClientTopic topic1= new ClientTopic("seven","BPM","Bizflow");
		 //ClientTopic topic2= new ClientTopic("eight","ECM","filenet");
		 //listOfClientTopics.add(topic1);
		// listOfClientTopics.add(topic2);
		 //to add list of Objects
		 //addListTopic(listOfClientTopics,restTemplate,log);
		
	}

	

	










	private static void getClientTopics(RestTemplate restTemplate, Logger log) {
		ResponseEntity<List<ClientTopic>> topicsResponse =
		        restTemplate.exchange("http://localhost:8080/getTopics",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<ClientTopic>>() {
		            });
		List<ClientTopic> ClientTopicList = topicsResponse.getBody();
		System.out.println("=================Get all topics Start=====================");
		log.debug("=================Get all topics start=====================");
		
		for(ClientTopic ClientTopicList1 : ClientTopicList) {
			System.out.println("Topic List");
            System.out.println(ClientTopicList1.getName()+"   "+ClientTopicList1.getId()+"   "+ClientTopicList1.getDescription());
            log.debug("Topic List");
            log.debug(ClientTopicList1.getName()+"   "+ClientTopicList1.getId()+"   "+ClientTopicList1.getDescription());
        }
		
		
		
		
		System.out.println("=================Get all topics End=====================");
		log.debug("=================Get all topics End=====================");
	}

	private static void getClientTopicById(String id,RestTemplate restTemplate,Logger log) {
		
		// TODO Auto-generated method stub
		System.out.println("=================Get  topic by ID Start=====================");
		log.debug("=================Get  topic by ID Start=====================");
		
		final String uri = "http://localhost:8080/getTopic/{id}";
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", id);
	     
	    //the below method returns Object not JSON, we can use this to directly assign the returned object to the pojo class which is also correct.
	    //ClientTopic clientTopic = restTemplate.getForObject(uri, ClientTopic.class, params);
	    
	    
	    //the below method returns response entity with body(JSON object) along with status and headers ,we can fetch the requires parameters from the respnse entity ex we are fetching body below
	    ResponseEntity<ClientTopic> clientTopicEntity=restTemplate.getForEntity(uri,  ClientTopic.class,params);
	    ClientTopic clientTopic= clientTopicEntity.getBody();
	     
	    System.out.println(clientTopic);
		
		
	    log.debug("The client output is"+"   "+clientTopic.getId()+"   "+clientTopic.getName()+"    "+clientTopic.getDescription());
		
		System.out.println(("The client output is"+"    "+clientTopic.getId()+"   "+clientTopic.getName()+"    "+clientTopic.getDescription()));
		
		
		//ClientTopic clientTopic= restTemplate.getForObject("http://localhost:8080/getTopic/"+id, ClientTopic.class);
		
		
		
		
		
		System.out.println("=================Get  topic by ID End=====================");
		log.debug("=================Get  topic by ID End=====================");
	}
	
	private static void addTopic(ClientTopic ClientTopic,RestTemplate restTemplate,Logger log) {
	
		System.out.println("=================Add topic Start=====================");
		log.debug("=================Add Topic Start=====================");
		
		//below method is used to add a object, we are providing object to be added. this method returns the object,so the return type of controller method is the entity type
		//ClientTopic addedTopic =restTemplate.postForObject("http://localhost:8080/addTopic", ClientTopic, ClientTopic.class);
		
		//below method returns response entity
		 //ResponseEntity<ClientTopic> addedclientTopicEntity=restTemplate.postForEntity("http://localhost:8080/addTopic", ClientTopic, ClientTopic.class);
		   // ClientTopic addedTopic= addedclientTopicEntity.getBody();
		    
		//below method uses exchange method which also returns response entity but with input as object hhtp entity not object
			HttpEntity<ClientTopic> addRequest = new HttpEntity<ClientTopic>(ClientTopic);
		    
		    ResponseEntity<ClientTopic> addedclientTopicEntity = restTemplate
		    		  .exchange("http://localhost:8080/addTopic", HttpMethod.POST, addRequest, ClientTopic.class);
		    
		    ClientTopic addedTopic= addedclientTopicEntity.getBody();
		
		log.debug("The client added Topic with details"+"   "+addedTopic.getId()+"   "+addedTopic.getName()+"    "+addedTopic.getDescription());
		
	
		
		System.out.println(("The client added Topic with details "+"    "+addedTopic.getId()+"   "+addedTopic.getName()+"    "+addedTopic.getDescription()));
	
	System.out.println("=================Add topic End=====================");
	log.debug("=================Add Topic End=====================");
	
	
	}
	
	private static void updateTopic(ClientTopic clientTopic, RestTemplate restTemplate, Logger log,String id) {
		// TODO Auto-generated method stub
		
		System.out.println("=================Update topic Start=====================");
		log.debug("=================Update Topic Start=====================");
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//    	headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<ClientTopic> requestEntity = new HttpEntity<ClientTopic>(clientTopic, headers);
//
//         restTemplate.exchange("http://localhost:8080/updateTopic/"+id, HttpMethod.POST, requestEntity, ClientTopic.class);
	
         
         
         final String uri = "http://localhost:8080/updateTopic/{id}";
         
         Map<String, String> params = new HashMap<String, String>();
         params.put("id", id);
          
         
          //below method updateds the entity,which is also correct
         //restTemplate.put ( uri, clientTopic, params);
         
         
         //below method is using exchange method
         HttpEntity<ClientTopic> updateRequest = new HttpEntity<>(clientTopic);
         
         ResponseEntity<ClientTopic> updatedclientTopicEntity = restTemplate
	    		  .exchange(uri, HttpMethod.PUT, updateRequest, ClientTopic.class,params);
	    
	    ClientTopic updatedTopic= updatedclientTopicEntity.getBody();
	    
	    
	    log.debug("The client added Topic with details"+"   "+updatedTopic.getId()+"   "+updatedTopic.getName()+"    "+updatedTopic.getDescription());
		
	
		
		System.out.println(("The client added Topic with details "+"    "+updatedTopic.getId()+"   "+updatedTopic.getName()+"    "+updatedTopic.getDescription()));
         
         
	
	System.out.println("=================update topic End=====================");
	log.debug("=================Update Topic End=====================");
		
	}
	private static void deleteTopic(String deleteTopicId, RestTemplate restTemplate, Logger log) {
		// TODO Auto-generated method stub
		System.out.println("=================delete  topic by ID Start=====================");
		log.debug("=================delete  topic by ID Start=====================");
		restTemplate.delete("http://localhost:8080/deleteTopic/"+deleteTopicId);
		
		
		System.out.println("=================delete  topic by ID End=====================");
		log.debug("=================delete  topic by ID End=====================");
	}
	
	
	
	

	private static void addListTopic(List<ClientTopic> listOfClientTopics, RestTemplate restTemplate, Logger log) {
		// TODO Auto-generated method stub
		
		
		for(int i =0;i<listOfClientTopics.size();i++)
		{
			
			HttpEntity<ClientTopic> addlistRequest = new HttpEntity<ClientTopic>(listOfClientTopics.get(i));
		    
		    ResponseEntity<ClientTopic> addedclientListTopicEntity = restTemplate
		    		  .exchange("http://localhost:8080/addTopic", HttpMethod.POST, addlistRequest, ClientTopic.class);
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
