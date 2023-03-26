package mx.gob.imss.cit.vcvd.correo.exceptions.integration.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CorreoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1267416571603324L;
	
	@NotNull (message = "La lista de destinatarios no puede ser null, verifique.")
	@NotEmpty (message = "La lista de destinatarios no puede estar vacía, verifique.")
	List<String> destinatarios;
	List<String> copiasOcultas;
	List<String> copias;
	@NotNull (message = "El campo cuerpoCorreo no puede ser null, verifique.")
	String cuerpoCorreo;
	byte[] adjunto;
	String nombreAdjunto;
	String extensionAdjunto;
	@NotNull (message = "El campo asunto no puede ser null, verifique.")
	String asunto;
	String correoDe;
	String correoDeAlias;
}
