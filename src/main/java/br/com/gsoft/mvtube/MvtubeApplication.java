package br.com.gsoft.mvtube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@SpringBootApplication
public class MvtubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvtubeApplication.class, args);
	}

}
