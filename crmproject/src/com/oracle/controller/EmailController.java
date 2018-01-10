package com.oracle.controller;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {

	@Resource(name = "mailSender")
	private JavaMailSenderImpl mailSender;
	@Resource(name = "templateMessage")
	private SimpleMailMessage mailMessage;

	@RequestMapping("/sendMail")
	public String sendMail() throws InterruptedException {
		String mailContent = "������,�ֵ�ע�Ᵽů";
		// �ʼ����͸�˭
		String[] str={"1253560955","2113612062","2249308227","314775416","991506310","321311609","1697315656"};
		
		for(int i=0;i<5;i++){
			mailMessage.setTo("@qq.com");
			// �ʼ�����
			mailMessage.setText(mailContent);
			// �ʼ�����
			mailMessage.setSubject("�ʺ�");
			
			
			this.mailSender.send(mailMessage);// ����
		}
		
		
		return "";
	}

}
