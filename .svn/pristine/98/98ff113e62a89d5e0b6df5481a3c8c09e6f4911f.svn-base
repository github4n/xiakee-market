package com.xiakee.service.smsc.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.xiakee.service.smsc.AccountEmailService;

@Service("accountEmail")
public class AccountEmailServiceImpl implements AccountEmailService {
	private static final Logger log = Logger.getLogger(AccountEmailServiceImpl.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${email.systemEmail}")
	private String systemEmail;
	@Value("${email.isSendEmail}")
	private boolean isOpenSendEmail;
	
	@Override
	public void sendMail(String to, String subject, String htmlText)
			throws Exception {
		try {
//			MimeMessage msg = this.javaMailSender.createMimeMessage();
//			MimeMessageHelper msgHelper = new MimeMessageHelper(msg);
//			
//			msgHelper.setFrom(this.systemEmail);
//			msgHelper.setText(to);
//			msgHelper.setSubject(subject);
//			msgHelper.setText(htmlText,true);
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(systemEmail);
			msg.setTo(to);
			msg.setSubject(subject);
			msg.setText(htmlText);
			this.javaMailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isOpenSendEmail() {
		// TODO Auto-generated method stub
		return isOpenSendEmail;
	}
}
