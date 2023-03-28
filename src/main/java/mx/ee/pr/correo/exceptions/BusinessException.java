package mx.ee.pr.correo.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import mx.ee.pr.correo.enums.EnumHttpStatus;
import mx.ee.pr.correo.exceptions.integration.dto.RespuestaError;

@Getter
@Setter
public class BusinessException extends Exception implements Serializable{
	
	private RespuestaError respuestaError;

	public BusinessException(EnumHttpStatus status,
			String businessMessage,
			String reasonPhrase,
			String cveBusinessMessage,
			String internalCode) {

		super(reasonPhrase);

		String completeBusinessMessage = businessMessage + " (" + internalCode + ")";

		respuestaError = new RespuestaError(status, completeBusinessMessage, reasonPhrase, cveBusinessMessage);

	}

	public BusinessException(RespuestaError respuestaError) {
		this.respuestaError = respuestaError;
	}

	private static final long serialVersionUID = 1L;
}
