package system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BaseDtoException extends RuntimeException {
	
	public BaseDtoException(String messagge) {
		super(messagge);
	}
	
	public BaseDtoException(String messagge, Throwable cause) {
		super(messagge, cause);
	}
 
}
