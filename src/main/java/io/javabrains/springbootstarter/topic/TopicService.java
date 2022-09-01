package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	@Autowired
	private TopicRepository topicRepository;
	
	

	private List<Topic> topics = new ArrayList<Topic>(Arrays.asList(new Topic("one","java","core java"),new Topic("two","spring","spring")));
	
	public List<Topic> getTopicsService()
	{
		
		 List<Topic> topics = new ArrayList<Topic>();
		 topicRepository.findAll().forEach(topics::add);
		 
		return topics;
	}
	
	
	


	public Topic getTopicServiceByID(String id) {
		// TODO Auto-generated method stub
		//return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		
		return topicRepository.findOne(id);
		
		
	}





	public Topic addTopicsService(Topic topic) {
		// TODO Auto-generated method stub
		//topics.add(topic);
		
		return topicRepository.save(topic);
	}





	public Topic updateTopicsService(Topic topic, String id) {
		// TODO Auto-generated method stub
//		for(int i = 0;i<topics.size();i++)
//		{
//			if(topics.get(i).getId().equals(id))
//			{
//				topics.set(i, topic);
//			}
//		}
		
		return topicRepository.save(topic);
	}





	public void deleteTopicsService(String id) {
		//topics.removeIf(t -> t.getId().equals(id));
		topicRepository.delete(id);
}
}
