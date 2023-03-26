package mx.gob.imss.cit.vcvd.correo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.gob.imss.cit.vcvd.correo.exceptions.BusinessException;
import mx.gob.imss.cit.vcvd.correo.exceptions.integration.dto.CorreoDto;
import mx.gob.imss.cit.vcvd.correo.exceptions.integration.dto.RespuestaError;
import mx.gob.imss.cit.vcvd.correo.service.CorreoService;

@RestController
@RequestMapping("/msvcvd-correo")
@Validated
@CrossOrigin
public class CorreoController {
	private static final Logger logger = LoggerFactory.getLogger(CorreoController.class);

	@RequestMapping("/health/ready")
	@ResponseStatus(HttpStatus.OK)
	public void ready() {}

	@RequestMapping("/health/live")
	@ResponseStatus(HttpStatus.OK)
	public void live() {}
	
	@Autowired
	CorreoService correoService;

	@ApiOperation(value = "Servicio que realiza el envío del correo con archivo adjunto.", notes = "Endpoint para realizar el envío de correo con archivo adjunto.", response = HttpStatus.class, tags={  })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Se devuelve el código una vez que el relay IMSS ha recibido el objeto correctamente.", response = HttpStatus.class),
			@ApiResponse(code = 500, message = "Describe un error general en el sistema.", response = RespuestaError.class) })
	@PostMapping(value = "/v1/correo", consumes = {"application/json" })	
	public Object enviarCorreo(@RequestBody @Valid CorreoDto body) {
		try {
			
			logger.info("Entrada a enviarCorreo: " + body.getDestinatarios());
			
			correoService.enviarCorreo(body);
			
			logger.info("Salida de enviarCorreo: " + body.getDestinatarios());
			
			
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
			
		} catch (BusinessException be) {
			logger.info("Error en mssaiprov-entrada: ", be);
			int numberHTTPDesired = Integer.parseInt(be.getRespuestaError().getCode());
			RespuestaError respuestaError = be.getRespuestaError();
			return new ResponseEntity<RespuestaError>(respuestaError, HttpStatus.valueOf(numberHTTPDesired));
		}
	}
}
