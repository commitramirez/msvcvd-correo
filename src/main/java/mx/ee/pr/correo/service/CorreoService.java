package mx.ee.pr.correo.service;

import mx.ee.pr.correo.exceptions.BusinessException;
import mx.ee.pr.correo.exceptions.integration.dto.CorreoDto;

public interface CorreoService {
	
	public void enviarCorreo(CorreoDto correo) throws BusinessException;
}
