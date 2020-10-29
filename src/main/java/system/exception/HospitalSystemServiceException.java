package system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class HospitalSystemServiceException extends RuntimeException {
	
	public HospitalSystemServiceException(String messagge) {
		super(messagge);
	}
	

	public HospitalSystemServiceException(String messagge,Throwable cause) {
		super(messagge,cause);
	}
}
