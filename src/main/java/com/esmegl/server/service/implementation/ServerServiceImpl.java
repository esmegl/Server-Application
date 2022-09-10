package esmegl.server.service.implementation;

import esmegl.server.repository.ServerRepo;
import esmegl.server.enumeration.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Collection;
import java.net.InetAddress;
import java.io.IOExeption;
// import java.lang.String;
// import java.lang.Boolean.TRUE;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
	private final ServerRepo serverRepo;

	@Override
	public Server create(Server server) {
		log.info("Saving new server: {}", server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepo.save(server);
	}

	@Override
	public Server ping(String ipAddress) throws IOExeption {
		log.info("Pinging server IP: {}", ipAddress);
		Server server  = serverRepo.findByIpAddress(ipAddress);
		InetAddress address = InetAddress.getByName(ipAddress);
		server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
		serverRepo.save(server);
		return server;
	}

	@Override
	public Collection<Server> List(int limit) {
		log.info("Fetching all servers...");
		return serverRepo.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server get(Long id) {
		log.info("Fetching server by ID: {}", id);
		return serverRepo.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating server: {}", server.getName());
		return serverRepo.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by ID: {}", id);
		serverRepo.deleteById(id);
		return TRUE;
	}

	private String setServerImageUrl() {
		String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"}; 
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/assets/" + imageNames[new Random().nextInt(4)]).toUriString();
	}

}