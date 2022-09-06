package com.example.repository;

import com.example.PersistenceConfig;
import com.example.WebConfig;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
class BuyingJpaRepositoryTest {
    @Autowired
    BuyingJpaRepository repository;

    @Test
    @DatabaseSetup("/database/setup/buying.xml")
    void findByCustomerAndBook() {
        repository.findByCustomerAndBook(1L, 1L);
    }
}