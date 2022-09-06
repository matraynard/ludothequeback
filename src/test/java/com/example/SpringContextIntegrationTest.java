package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@ContextConfiguration(
        classes = {WebConfig.class, PersistenceConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class SpringContextIntegrationTest {
    @Test
    public void contextLoads(){
        // When
    }
}
