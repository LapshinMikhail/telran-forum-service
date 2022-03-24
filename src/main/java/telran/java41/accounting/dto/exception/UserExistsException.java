package telran.java41.accounting.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5166427865391296505L;

	public UserExistsException(String login) {
		super("User " + login + " exists");
	}
}
