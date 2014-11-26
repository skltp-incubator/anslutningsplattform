package se.skltp.av.service

import javax.annotation.PostConstruct
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MailingService {
	
	def grailsApplication
	
	def send(String fromAddress, String subjectField, String bodyPlainText) {
		def toAddress = grailsApplication.config.smtp.to.address
		
		// uses the mail-plugin: http://grails.org/plugin/mail
		sendMail{
			to toAddress
			from fromAddress
			subject subjectField
			body bodyPlainText
		}
	}
}
