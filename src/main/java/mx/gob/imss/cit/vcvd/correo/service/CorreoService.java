package mx.gob.imss.cit.vcvd.correo.service;

import mx.gob.imss.cit.vcvd.correo.exceptions.BusinessException;
import mx.gob.imss.cit.vcvd.correo.exceptions.integration.dto.CorreoDto;

public interface CorreoService {
	
	public void enviarCorreo(CorreoDto correo) throws BusinessException;
}
