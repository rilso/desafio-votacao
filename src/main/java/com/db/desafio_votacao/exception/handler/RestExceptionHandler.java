package com.db.desafio_votacao.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.db.desafio_votacao.exception.ErrorResponse;
import com.db.desafio_votacao.exception.LimiteSessaoPautaException;
import com.db.desafio_votacao.exception.LimiteVotoAssociadoException;
import com.db.desafio_votacao.exception.PautaInexistenteException;
import com.db.desafio_votacao.exception.PautaSemSessaoAtivaException;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> details = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map((error) -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());


        ErrorResponse errorResponse = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(LimiteSessaoPautaException.class)
	public ResponseEntity<String> handleLimiteSessaoPautaException(LimiteSessaoPautaException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(PautaInexistenteException.class)
	public ResponseEntity<String> handleLimiteSessaoPautaException(PautaInexistenteException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(PautaSemSessaoAtivaException.class)
	public ResponseEntity<String> handleLimiteSessaoPautaException(PautaSemSessaoAtivaException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(LimiteVotoAssociadoException.class)
	public ResponseEntity<String> handleLimiteSessaoPautaException(LimiteVotoAssociadoException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
