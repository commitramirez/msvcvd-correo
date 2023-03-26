package mx.ee.pr.correo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import mx.ee.pr.correo.enums.EnumBusinnessError;
import mx.ee.pr.correo.enums.EnumHttpStatus;
import mx.ee.pr.correo.exceptions.BusinessException;
import mx.ee.pr.correo.exceptions.integration.dto.CorreoDto;
import mx.ee.pr.correo.service.CorreoService;

@Service
public class CorreoServiceImpl implements CorreoService {

	private static final Logger logger = LoggerFactory.getLogger(CorreoServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void enviarCorreo(CorreoDto correo) throws BusinessException {

		try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setHeader("Content-Type", "text/html;charset=UTF-8");

			MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
			mail.setFrom(correo.getEmailFrom(), correo.getEmailFromAlias());

			String[] destinatarios = correo.getRecipientList().toArray(new String[0]);
			mail.setTo(destinatarios);
			mail.setSubject(correo.getSubject());

			if (correo.getCcoList() != null && !correo.getCcoList().isEmpty()) {
				String[] copiasOcultas = correo.getCcoList().toArray(new String[0]);
				mail.setBcc(copiasOcultas);
			}

			if (correo.getCcList() != null && !correo.getCcList().isEmpty()) {
				String[] copias = correo.getCcList().toArray(new String[0]);
				mail.setCc(copias);
			}

			if (correo.getAttachment() != null) {
				File tmpFile = File.createTempFile(correo.getAttachmentName(), correo.getAttachmentExt());

				try (FileOutputStream fileOuputStream = new FileOutputStream(tmpFile)) {
					fileOuputStream.write(correo.getAttachment());
				} catch (IOException e) {
					e.printStackTrace();
				}

				mail.addAttachment(correo.getAttachmentName() + correo.getAttachmentExt(), tmpFile);
			}

			mail.setText(new String(correo.getEmailBody().getBytes(), StandardCharsets.UTF_8.name()), true);
			mimeMessage.setContentID("text/html; charset=UTF-8");
			mailSender.send(mimeMessage);

		} catch (Exception e) {
			logger.info("Error en enviarCorreo Service: ", e);
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL,
					EnumBusinnessError.SERVER_ERROR_INTERNAL.getDescripcion(), e.getMessage(),
					EnumBusinnessError.SERVER_ERROR_INTERNAL.getCodigo().toString(),
					EnumBusinnessError.SERVER_ERROR_INTERNAL.getCodigo().toString());
		}
	}
}
