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
import org.springframework.web.bind.annotation.RestController;

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

	@Autowired
	CorreoService correoService;

	@PostMapping(value = "/v1/correo", consumes = { "application/json" })
	public Object enviarCorreo(@RequestBody @Valid CorreoDto body) {
		try {

			logger.info("Entrada a enviarCorreo: " + body.getRecipientList());

			correoService.enviarCorreo(body);

			logger.info("Salida de enviarCorreo: " + body.getRecipientList());

			return new ResponseEntity<>(true, HttpStatus.OK);

		} catch (BusinessException be) {
			int numberHTTPDesired = Integer.parseInt(be.getRespuestaError().getCode());
			RespuestaError respuestaError = be.getRespuestaError();
			return new ResponseEntity<RespuestaError>(respuestaError, HttpStatus.valueOf(numberHTTPDesired));
		}
	}
}
