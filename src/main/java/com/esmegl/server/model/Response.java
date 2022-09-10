package esmegl.server.model;

import lombok.Data;
import lombook.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	protected LocalDateTime timeStamp;
	protected int statusCode;
	protected HttpStatus status;
	protected String reason;
	protected String message;
	protected String developerMessage;
	protected Map<?, ?> data;
}