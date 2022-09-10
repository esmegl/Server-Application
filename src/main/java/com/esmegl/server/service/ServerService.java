package esmegl.server.service;

import import esmegl.server.model.Server;
import java.lang.Boolean;

import java.util.Collection;

public interface ServerService {
	Server create(Server server);
	Server ping(String ipAddress);
	Collection<Server> List(int limit);
	Server get(Long id);
	Server update(Server server);
	Boolean delete(Long id);
}