package mx.ee.pr.correo.exceptions.integration.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import mx.ee.pr.correo.enums.EnumHttpStatus;

@Data
public class RespuestaError {
	private String code;
	private String description;
	private String businessMessage;
	private String uri;
	private String contactEmail;
	private String timeStamp;

	public RespuestaError(EnumHttpStatus status, String businessMessage, String reasonPhrase, String cveMessage) {
		this.code = status.getCode().toString();
		this.description = status.getDescription();
		this.businessMessage = businessMessage;
		this.uri = "http://urlDeMensaje/help?cveMessage=" + cveMessage;
		this.contactEmail = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		this.timeStamp = dateFormat.format(new Date());

	}	 
}
