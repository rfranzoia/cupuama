package br.com.cupuama.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDateRange extends Exception {
	static final long serialVersionUID = -1L;

	public InvalidDateRange(String message) {
		super(message);
	}

	public InvalidDateRange(String message, Throwable cause) {
		super(message, cause);
	}
}
