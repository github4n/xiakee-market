package com.xiakee.service.smsc;

import org.apache.log4j.Logger;

import com.xiakee.domain.smsc.EmailContentBean;
import com.xiakee.service.AutoExecuteTask;

public class EmailAutoSendTask extends AutoExecuteTask<EmailContentBean> {
	private static Logger log = Logger.getLogger(EmailAutoSendTask.class);

	private AccountEmailService emailService;

	public AccountEmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(AccountEmailService emailService) {
		this.emailService = emailService;
	}

	@Override
	public boolean executeTask() throws Exception {
		boolean isOK = false;
		if (getTaskBean() != null) {
			if (emailService.isOpenSendEmail()) {
				log.info("=========正在发送邮件：============" + getTaskBean());
				this.emailService.sendMail(getTaskBean().getTo(), getTaskBean()
						.getSubject(), getTaskBean().getHtmlText());
				isOK = true;
			} else {
				log.info("=============系统没有开启邮件发送功能============"
						+ getTaskBean());
			}
		}
		return isOK;
	}

}
