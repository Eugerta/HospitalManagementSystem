package system.exception;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class SystemExceptionHandler {
	
	private final Logger logger= LogManager.getLogger(getClass());
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseErrorResponseDto handleArgumentInvalid(MethodArgumentNotValidException ex) {
		
		BaseErrorResponseDto errorResponse = BaseErrorResponseDto.builder()
				.timestamp(new Date())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.name())
				.messagge("Paramter request is not valid "+ ex.getBindingResult().getFieldError().getDefaultMessage())
				.build();
		return errorResponse;
				
	}
	
	public BaseErrorResponseDto handleGenericException(Exception ex) throws Exception{
		
		checkExceptionMessageAndLogIt(logger,ex);
		
		
		if(ex instanceof HospitalSystemServiceException || ex instanceof BaseDtoException)
			throw ex;
		
		BaseErrorResponseDto errorResponse = BaseErrorResponseDto.builder()
				.timestamp(new Date())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.build();
		if(StringUtils.isNotBlank(ex.getMessage()))
			errorResponse.setMessagge("Error during execution of service: "+ex.getMessage());
		else 
			errorResponse.setMessagge("General Error");
		
		return errorResponse;
		
		
	}
	
	
	public static void checkExceptionMessageAndLogIt(Logger logger, Exception ex) {
		
		String exceptionMessagge = "";
		
		if(StringUtils.isNotBlank(ex.getMessage()))
			exceptionMessagge = ex.getMessage();
		
		logger.error(exceptionMessagge, ex);
		
	}
	

}
