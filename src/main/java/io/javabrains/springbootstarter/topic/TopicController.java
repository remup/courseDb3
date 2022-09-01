package io.javabrains.springbootstarter.topic;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.ExceptionHandling.StudentNotFoundException;

@RestController
public class TopicController {
     
	@Autowired
	private TopicService service;
	private static final Logger LOGGER=LoggerFactory.getLogger(TopicController.class);
	
	
	//@RequestMapping("/getTopics")
	 @RequestMapping(value="/getTopics", method=RequestMethod.GET,
     produces="application/json")
	public ResponseEntity<List<Topic>> getTopics()
	{
		 HttpHeaders headers = new HttpHeaders();
		  List<Topic> listOfTopic =service.getTopicsService();
		  if (!listOfTopic.isEmpty()) {
			  
		        headers.add("DataPrsent", "yes");
		       return ResponseEntity.ok().headers(headers).body(listOfTopic);
		    }else{
		    	headers.add("DataPrsent", "No");
		    	return ((BodyBuilder) ResponseEntity.notFound().headers(headers)).body(listOfTopic);
		        //throw new NotFoundException("Applicants not found");
		    }
		
		
	}
	 
	 @RequestMapping(value="/getTopic/{id}", method=RequestMethod.GET,
		     produces="application/json")
			public ResponseEntity<Topic> getTopic(@PathVariable String id)
			{
				 //return service.getTopicServiceByID(id);
				 HttpHeaders headers = new HttpHeaders();
			        headers.add("Responded", "MyControllerGetTopicByID");
			        Topic t=service.getTopicServiceByID(id);
			        //LOGGER.info("I am Inside getTopicFromID");
			        if(t==null)
			        {
			        	LOGGER.error("Topic with this id-" + id+"    "+"not found");
			        	throw new StudentNotFoundException("Topic with this id-" + id+"    "+"not found");
			        //return ResponseEntity.accepted().headers(headers).body(t);
			        }
			        else
			        {
			        	LOGGER.debug("data from DB for"+"Topic Id"+" "+id+"details"+" "+t.getId()+" "+t.getName()+" "+t.getDescription());
			        	//return ResponseEntity.ok().headers(headers).body(t);
			        	 return new ResponseEntity<Topic>(t,headers, HttpStatus.OK);
			        }
				
				
			}
	 
	 @RequestMapping(value="/addTopic", method=RequestMethod.POST)
			public ResponseEntity<Topic> addTopics(@RequestBody Topic topic)
			{
		 Topic topicAdded =service.addTopicsService(topic);
		 return new  ResponseEntity<Topic>(topicAdded, HttpStatus.OK);
				
				 
				
				
			}
	 
	 @RequestMapping(value="/updateTopic/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic,@PathVariable String id)
		{
			  Topic topicUpdate =service.updateTopicsService(topic,id);
			 return new  ResponseEntity<Topic>(topicUpdate, HttpStatus.OK);
			 
			
			
		}
	 
	 @RequestMapping(value="/deleteTopic/{id}", method=RequestMethod.DELETE)
		public void deleteTopic(@PathVariable String id)
		{
			  service.deleteTopicsService(id);
			 
			
			
		}
	
}
