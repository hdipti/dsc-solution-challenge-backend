package com.au.sc2021;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.au.sc2021.model.Candidate;


@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // classes = Application.class,
public class CandidateControllerIntegrationTest {
     @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:8080/api";//"http://localhost:" + port;
     }

     @Test 
     public void contextLoads() {

     }

     @Test
     public void testGetAllCandidates() {
     HttpHeaders headers = new HttpHeaders();
     headers.add("Access-Control-Allow-Origin", "*");
     headers.add("Access-Control-Allow-Headers", "X-API-KEY, Origin, X-Requested-With, Content-Type, Accept");
     headers.add("Access-Control-Allow-Methods", "GET");
     headers.add("Content-Type", "application/ms-excel");
        HttpEntity<String> entity = new HttpEntity<String>("candidates", headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/api/candidates", HttpMethod.GET, entity, String.class);  
        System.out.println("\n");
        System.out.println(response);
        System.out.println("\n");
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetCandidateByLoginDetails() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", "X-API-KEY, Origin, X-Requested-With, Content-Type, Accept");
        headers.add("Access-Control-Allow-Methods", "GET");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Candidate> Candidate = restTemplate.exchange("http://localhost:8080/api/login?username=abcd&password=123456", HttpMethod.GET, entity, Candidate.class);
        System.out.println("\nTest2");
        System.out.println(Candidate.getBody());
        System.out.println("\n");
      //  System.out.println(Candidate.getFirstName());
        assertNotNull(Candidate);
    }

}