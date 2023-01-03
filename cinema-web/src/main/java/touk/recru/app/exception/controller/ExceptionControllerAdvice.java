package touk.recru.app.exception.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;

@ControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentException(MethodArgumentNotValidException e) {
		StringBuilder stringBuilder = new StringBuilder();
		for (ObjectError error : e.getBindingResult()
				.getAllErrors()) {
			stringBuilder.append(error.getDefaultMessage())
					.append("\n");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(stringBuilder.toString());
	}


	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
		// Create a response entity with a custom error message
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(processMessage(ex));
	}

	private String processMessage(ConstraintViolationException ex) {
		StringBuilder sb = new StringBuilder();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		for (ConstraintViolation<?> violation : violations) {
			sb.append("Validation error for property: '")
					.append(getFieldName(violation))
					.append("': ")
					.append(violation.getMessage())
					.append("\n");
		}
		return sb.toString();
	}

	private String getFieldName(ConstraintViolation<?> violation) {
		String[] parts = violation.getPropertyPath()
				.toString()
				.split("\\.");
		if (parts[parts.length - 1].charAt((0)) == '<') {
			return parts[parts.length - 2];
		}
		return parts[parts.length - 1];
	}

}
