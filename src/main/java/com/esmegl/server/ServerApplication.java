package com.esmegl.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.esmegl.server.repository.ServerRepo;
import com.esmegl.server.model.Server;
import com.esmegl.server.enumeration.Status;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	//This will run after the application has been initalized
	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(
				null, 
				"192.168.1.160", 
				"Ubuntu Linux", 
				"16 GB", 
				"PC",
				"http://localhost:8080/server/assets/server1.png",
				Status.SERVER_UP));

			serverRepo.save(new Server(
				null, 
				"192.168.1.58", 
				"Fedora Linux", 
				"16 GB", 
				"Dell Tower",
				"http://localhost:8080/server/assets/server2.png",
				Status.SERVER_UP));

			serverRepo.save(new Server(
				null, 
				"192.168.1.21", 
				"MS 2008", 
				"32 GB", 
				"Web Server",
				"http://localhost:8080/server/assets/server3.png",
				Status.SERVER_UP));

			serverRepo.save(new Server(
				null, 
				"192.168.1.14", 
				"Red Hat Enterprise Linux", 
				"64 GB", 
				"Mail Server",
				"http://localhost:8080/server/assets/server4.png",
				Status.SERVER_UP));
		};
	}

}
