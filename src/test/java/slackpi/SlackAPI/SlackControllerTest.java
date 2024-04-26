package slackpi.SlackAPI;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SlackControllerTest {

	@MockBean
	private SlackService service;

	@Autowired
    private MockMvc mockMvc;

	@Test
	public void testSendMessage2() throws Exception {
		String message = "Test message";
		
		mockMvc.perform(
				post("/api/message").content(message)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("Message send!"));
		
		verify(service).sendMessage(message);
		
	}

}
