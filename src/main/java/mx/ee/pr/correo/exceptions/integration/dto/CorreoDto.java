package mx.ee.pr.correo.exceptions.integration.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CorreoDto implements Serializable{
	
	@NotNull (message = "La lista de destinatarios no puede ser null, verifique.")
	@NotEmpty (message = "La lista de destinatarios no puede estar vacía, verifique.")
	private List<String> recipientList;
	private List<String> ccoList;
	private List<String> ccList;
	//@NotNull (message = "El campo cuerpoCorreo no puede ser null, verifique.")
	//private String emailBody;
	private byte[] attachment;
	private String attachmentName;
	private String attachmentExt;
	@NotNull (message = "El campo asunto no puede ser null, verifique.")
	private String subject;
	private String emailFrom;
	private String emailFromAlias;
	
	private String emailTemplate;
	private Map<String, Object> contextVar;
	
	private static final long serialVersionUID = 1L;
}
