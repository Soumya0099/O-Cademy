package book.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OnlineBookStoreBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookStoreBooksApplication.class, args);
	}

}
