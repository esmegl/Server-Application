package esmegl.server.model

import esmegl.server.enumeration.Status;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
	@Id 
	@GeneratedValue(strategy = AUTO)
	private Long id;
	@Column(unique = true)
	@NotEmpty(message = "IP address cannot be ampty or null")
	private String ipAddress;
	private String name;
	private String memory;
	private String type;
	private String imageUrl;
	private Status status;
}