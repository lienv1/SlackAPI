package slackpi.SlackAPI.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	//Only this IP can access the API, if nothing is set, then the API is globally accessible
	@Value("${HOST_IP:}")
	private String host;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		RequestMatcher ipMatcher;
		
		if (host == null || host.isEmpty())
			ipMatcher = request -> {return true;};
		else //Only this ip can access this api
			ipMatcher = new IpAddressMatcher(this.host);

		//Make API only accessible in specific url, else it can be called from any root url
//		RequestMatcher testMatcher = request -> {return true;};
//        RequestMatcher domainMatcher = request -> {
//            String host = request.getHeader("Host");
//            return host != null && host.equals("localhost");
//        };
//        RequestMatcher combinedMatcher = new AndRequestMatcher(ipMatcher, domainMatcher);

		return http.csrf(csrf -> csrf.ignoringRequestMatchers("/**"))
				.authorizeHttpRequests(
						request -> request.requestMatchers(ipMatcher).permitAll().anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
	}
}