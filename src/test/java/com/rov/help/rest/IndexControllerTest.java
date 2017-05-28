package com.rov.help.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rov on 3/15/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IndexControllerTest {

    public static final String HI_THERE_TIFFANICHKA_FROM_APP = "hi there Tiffanichka from app";
    @Autowired
    private MockMvc mvc;

    @Value("${app.name}")
    private String name;

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void index() throws Exception {
        String expectedResponse = "hi there Tiffanichka from app" + name;
        mvc.perform(MockMvcRequestBuilders.get("/home")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedResponse)));
    }

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/home");
    }

    @Test
    public void indexRest()throws Exception{
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
        String expectedResponse = HI_THERE_TIFFANICHKA_FROM_APP + name;
        assertThat(response.getBody(), equalTo(expectedResponse));
    }

    @After
    public void cleanUp(){
        this.base = null;
    }


}