package com.example;

import com.example.controller.BookController;
import com.example.entity.Book;
import com.example.repository.IBookJpaRepository;
import com.example.services.BookService;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EnableJpaRepositories("com.example.repository")
@EntityScan("com.example.entity")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication demoApp = new SpringApplication(DemoApplication.class);

		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example");
		context.refresh();

		BaseBeanController bbc = context.getBean(bbc);*/

		demoApp.setBannerMode(Banner.Mode.OFF);
		demoApp.run(args);

		/*BookService bs = new BookService();
		List<Book> books = bs.findAll();
		books.stream().forEach(b -> System.out.println("id : " + b.getId() + "; title : " + b.getTitle() + "; rnumber : " + b.rnumber.getId()));*/
	}

	//@Override
	/*public void run(String... args) throws Exception {
		System.out.println("Hello World!");
	}*/

	/*@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}*/

	/*@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
	webServerFactoryCustomizer() {
		return factory -> factory.setContextPath("/hello");
	}*/
}