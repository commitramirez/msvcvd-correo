package mx.gob.imss.cit.vcvd.correo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import mx.gob.imss.cit.vcvd.correo.enums.EnumBusinnessError;
import mx.gob.imss.cit.vcvd.correo.enums.EnumHttpStatus;
import mx.gob.imss.cit.vcvd.correo.exceptions.BusinessException;
import mx.gob.imss.cit.vcvd.correo.exceptions.integration.dto.CorreoDto;
import mx.gob.imss.cit.vcvd.correo.service.CorreoService;

@Service
public class CorreoServiceImpl implements CorreoService{
	
	private static final Logger logger = LoggerFactory.getLogger(CorreoServiceImpl.class);
	
	@Value("${correo.relay}")
	String hostRelay;
	
	//@Autowired
    //private JavaMailSender mailSender;

	@Override
	public void enviarCorreo(CorreoDto correo) throws BusinessException {

		try {

			Properties properties = init();

			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();                                  

			mailSender.setHost(hostRelay);
			mailSender.setPort(Integer.valueOf(properties.getProperty("correo.server.port")));

			MimeMessage mimeMessage = mailSender.createMimeMessage();

			mimeMessage.setHeader("Content-Type", "text/html;charset=UTF-8");

			MimeMessageHelper mail;

			mail = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

			mail.setFrom(correo.getCorreoDe(), correo.getCorreoDeAlias());

			String[] destinatarios = correo.getDestinatarios().toArray(new String[0]);

			mail.setTo(destinatarios);

			mail.setSubject(correo.getAsunto());

			if (correo.getCopiasOcultas() != null && !correo.getCopiasOcultas().isEmpty() ) {

				String[] copiasOcultas = correo.getCopiasOcultas().toArray(new String[0]);

				mail.setBcc(copiasOcultas);

			}

			if (correo.getCopias() != null && !correo.getCopias().isEmpty() ) {

				String[] copias = correo.getCopias().toArray(new String[0]);

				mail.setCc(copias);

			}

			if(correo.getAdjunto() != null) {

				File tmpFile = File.createTempFile(correo.getNombreAdjunto(), correo.getExtensionAdjunto());

				try (FileOutputStream fileOuputStream = new FileOutputStream(tmpFile)) {
					fileOuputStream.write(correo.getAdjunto());
				} catch (IOException e) {
					e.printStackTrace();
				}

				mail.addAttachment(correo.getNombreAdjunto() + correo.getExtensionAdjunto(), tmpFile);
			}

			/* Bloque de código para pruebas de adjunto en UNIX 
			
			File currDir = new File(".");
			String path = currDir.getAbsolutePath();
			String fileLocation = path.substring(0, path.length() - 1) + correo.getNombreAdjunto() + correo.getExtensionAdjunto();
			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			outputStream.write(correo.getAdjunto());
			outputStream.close();
			
			*/

			mail.setText( new String (correo.getCuerpoCorreo().getBytes(), StandardCharsets.UTF_8.name()), true);
			mimeMessage.setContentID("text/html; charset=UTF-8");
			mailSender.send(mimeMessage);

		}catch (Exception e) {
			logger.info("Error en enviarCorreo Service: ", e);
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL,
					EnumBusinnessError.SERVER_ERROR_INTERNAL.getDescripcion(), e.getMessage(),
					EnumBusinnessError.SERVER_ERROR_INTERNAL.getCodigo().toString(), EnumBusinnessError.SERVER_ERROR_INTERNAL.getCodigo().toString());
		}
	}

	protected Properties init() {

		Properties properties = new Properties();

		properties.setProperty("correo.server.port","25");
		properties.setProperty("correo.server.mail.connectiontimeout","20000");
		properties.setProperty("correo.server.mail.timeout","20000");
		
		properties.setProperty("mail.smtp.starttls.enable","true");

		return properties;

	}
}


