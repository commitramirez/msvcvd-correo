package mx.ee.pr.correo.enums;

import lombok.Getter;
import lombok.Setter;

public enum EnumBusinnessError {

	SERVER_ERROR_INTERNAL (500, "El servidor está tardando más de lo normal."),
	CLIENT_ERROR_BAD_REQUEST (400, "La estructura del JSON es incorrecta, verifique.");
	@Getter
	@Setter
	private Integer codigo;
	@Getter
	@Setter	
	private String descripcion;

	EnumBusinnessError(Integer codigo, String descripcion)
	{
		this.codigo = codigo;
		this.descripcion = descripcion;

	}
}
