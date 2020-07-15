package proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public HttpHeaders getHttpHeaders() {
		return new HttpHeaders();
	}
	@Bean
	public HttpEntity<String> getHttpEntity() {
		return new HttpEntity<String>(getHttpHeaders());
	}

	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger(MirrorRest.class);
	}

}
