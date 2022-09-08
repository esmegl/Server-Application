package esmegl.server.repository

public interface ServerRepo extends JpaRepository<Server, Long>{
	Server findByIpAddress(String ipAddress);
}