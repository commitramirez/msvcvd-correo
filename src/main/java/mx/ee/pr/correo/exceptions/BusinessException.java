package mx.ee.pr.correo.exceptions;

import java.io.Serializable;

import mx.ee.pr.correo.enums.EnumHttpStatus;
import mx.ee.pr.correo.exceptions.integration.dto.RespuestaError;

public class BusinessException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5592959121778947867L;
	
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

	public RespuestaError getRespuestaError() {
		return respuestaError;
	}

	public void setRespuestaError(RespuestaError respuestaError) {
		this.respuestaError = respuestaError;
	}

}
