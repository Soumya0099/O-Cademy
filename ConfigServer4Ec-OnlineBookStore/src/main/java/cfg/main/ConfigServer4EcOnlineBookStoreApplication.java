package cfg.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer4EcOnlineBookStoreApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(ConfigServer4EcOnlineBookStoreApplication.class, args);
	}

}
