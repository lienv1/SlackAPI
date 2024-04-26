package slackpi.SlackAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import slackpi.SlackAPI.model.SlackMessage;

@Service
public class SlackService {
	
	private String slackurl;
	
	@Autowired
	private final Gson gson;
	
	public SlackService(@Value("${custom.property.slackurl}") String slackurl,  Gson gson) {
		this.slackurl = slackurl;
		this.gson = gson;
	}
	
	public void sendMessage(String message) {
		String requestJson = gson.toJson(new SlackMessage(message));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);
		
		String response = restTemplate.postForObject(slackurl, entity, String.class);
		
		System.out.println(response);
		 
	}

}
