package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestMicroService1ApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Autowired
	RestTemplate restTemplate;
    
    @LocalServerPort
    private int port;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCallMicroservice2() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo("http://localhost:8081/hello"))
			.andRespond(withSuccess("Hello from Microservice2", MediaType.TEXT_PLAIN));
		ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:"+port+"/microservice2", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Hello from Microservice2");
		mockServer.verify();
	}

}
