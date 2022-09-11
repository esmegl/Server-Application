package esmegl.server.resource;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus.OK;
import org.springframework.http.HttpStatus.CREATED;
import org.springframework.http.IMAGE_PNG_VALUE;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.Map;
import java.io.IOExeption;
import java.nio.file.Files;
import java.nio.file.Paths;

import esmegl.server.service.implementation.ServerServiceImpl;
import esmegl.server.model.Response;
import esmegl.server.model.Server;
import esmegl.server.enumeration.Status;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
	private final ServerServiceImplementation serverService;

	@GetMapping("/list")
	public ResponseEntity<Response> getServers() {
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("servers", serverService.list(30)))
				.message("Servers retrieved")
				.status(OK)
				.statusCode(OK.value())
				.build()
		);
	}

	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOExeption {
		Server server = serverService.ping(ipAddress);
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("server", server))
				.message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
				.status(OK)
				.statusCode(OK.value())
				.build()
		);
	}

	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("server", serverService.create()))
				.message("Server created")
				.status(CREATED)
				.statusCode(CREATED.value())
				.build()
		);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("server", serverService.get(id)))
				.message("Server retrieved")
				.status(OK)
				.statusCode(OK.value())
				.build()
		);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("deleted", serverService.delete(id)))
				.message("Server deleted")
				.status(OK)
				.statusCode(OK.value())
				.build()
		);
	}

	@GetMapping(path = "/assets/{fileName}", produces = IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOExeption {
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "git_projects/server/src/main/java/com/esmegl/server/assets" * fileName));
	}
}