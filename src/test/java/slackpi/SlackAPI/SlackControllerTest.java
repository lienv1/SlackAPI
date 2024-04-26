package slackpi.SlackAPI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class SlackControllerTest {

	@Mock
	private SlackService service;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private SlackController slackController;
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	public void testSendMessage() {
		String message = "Test message";
		ResponseEntity<String> response = slackController.sendMessage(message);

		verify(service).sendMessage(message);

		// Verify that the response is as expected
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Message send!", response.getBody());
	}

	@Test
	public void testSendMessage2() throws Exception {
		String message = "Test message";

		mockMvc.perform(
				post("/api/message").content(message).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("Message send!"));
	}

}
