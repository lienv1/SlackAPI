package slackpi.SlackAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.google.gson.Gson;

import slackpi.SlackAPI.model.SlackMessage;

@ExtendWith(MockitoExtension.class)
class SlackServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private Gson gson;

	@InjectMocks
	private SlackService slackService;

	@BeforeEach
	public void setup() {

		// Mock behavior for Gson
		when(gson.toJson(any(SlackMessage.class))).thenReturn("{\"text\": \"mocked message\"}");

		// Ensure proper initialization of the gson field in SlackService
		slackService = new SlackService("https://your-webhook", gson, restTemplate);

		// Mock behavior for RestTemplate
		when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class)))
				.thenReturn("mockedResponse");
	}

	@Test
	public void testSendMessage() {
		// Call the method under test
		slackService.sendMessage("Test message");

		// Verify that the RestTemplate.postForObject method was called with the
		// expected arguments
		verify(restTemplate).postForObject(anyString(), any(HttpEntity.class), eq(String.class));

		// You can add more assertions if needed
	}
}