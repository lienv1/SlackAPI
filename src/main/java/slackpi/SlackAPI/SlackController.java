package slackpi.SlackAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class SlackController {

	@Autowired
	private SlackService service;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String slackApiUrl;
	
	public SlackController(SlackService service, RestTemplate restTemplate, @Value("${custom.property.slackurl}") String slackApiUrl) {
		this.service = service;
		this.restTemplate = restTemplate;
		this.slackApiUrl = slackApiUrl;
	}

	//Testing purpose
	@GetMapping("/simple-message")
	public ResponseEntity<String> getMessage(HttpServletRequest request){
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}
	
	@PostMapping("/message")
	public ResponseEntity<String> sendMessage(@RequestBody String text ){
		service.sendMessage(text);
		return new ResponseEntity<String>("Message send!", HttpStatus.OK);
	}
	
	@PostMapping("/redirect")
    public ResponseEntity<String> redirectToExternalUrl(@RequestBody String body, @RequestHeader HttpHeaders headers) {
        HttpHeaders forwardedHeaders = new HttpHeaders();
        headers.forEach((key, value) -> {
            if (!"host".equalsIgnoreCase(key)) {
                forwardedHeaders.addAll(key, value);
            }
        });
        HttpEntity<String> requestEntity = new HttpEntity<>(body, forwardedHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(slackApiUrl, requestEntity, String.class);
        return ResponseEntity.status(response.getStatusCode())
                             .headers(response.getHeaders())
                             .body(response.getBody());
    }
	
}
