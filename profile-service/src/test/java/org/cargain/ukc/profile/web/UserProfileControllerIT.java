package org.cargain.ukc.profile.web;

import org.cargain.ukc.profile.ProfileServiceApplication;
import org.cargain.ukc.profile.domain.UserProfileDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProfileServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserProfileControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testCreateUserProfile() throws Exception {	
		
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<UserProfileDTO> response = restTemplate.exchange(
                createURLWithPort("/api/profiles/1"),
                HttpMethod.GET, entity, UserProfileDTO.class);

        String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";

      //  JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	 private String createURLWithPort(String uri) {
	        return "http://localhost:" + port + uri;
	    }
}
