package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(DemoApplicationTests.class)
/*@SpringBootTest
@AutoConfigureMockMvc*/
public class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /*@MockBean
    private IService service;

    @Test
    public void whenTestApp_thenEmptyResponse() throws Exception {
        this.mockMvc.perform(get("/foos")
                .andExpect(status().isOk())
                .andExpect(...);
    }*/

	/*@Test
	void contextLoads() {
	}*/
}