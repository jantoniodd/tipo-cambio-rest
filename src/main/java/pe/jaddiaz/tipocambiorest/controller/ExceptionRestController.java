package pe.jaddiaz.tipocambiorest.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pe.jaddiaz.tipocambiorest.controller.dto.BaseResponse;
import pe.jaddiaz.tipocambiorest.controller.dto.ErrorCode;

@ControllerAdvice
public class ExceptionRestController {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<BaseResponse> handleEntityNotFoundException() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(BaseResponse.error(ErrorCode.ENTITY_NOT_FOUND));
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<BaseResponse> handleEntityExistException() {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(BaseResponse.error(ErrorCode.ENTITY_EXIST));
	}

}
