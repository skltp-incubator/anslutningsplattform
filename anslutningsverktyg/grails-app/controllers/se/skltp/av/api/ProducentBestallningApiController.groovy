package se.skltp.av.api

import se.skltp.av.ProducentBestallning;
import grails.rest.RestfulController

class ProducentBestallningApiController extends RestfulController{

	static allowedMethods = [save: "POST"]
	
	static namespace = 'v1'

	static responseFormats = ['json', 'xml']
	
	def mailingService

	ProducentBestallningApiController() {
		super(ProducentBestallning)
	}
	
	def save() {
		// TODO: store to DB
		
		// TODO: extract the below params from POST
		//def fromAddress = "noreply.anslutningsplattform@ntjp.se"
		
		//NOT: testing with gmail requires:
		// 1. a non google-apps/enterprise mail account (see pt 2)
		// 2. allowing "less secure apps" using:
		//   https://www.google.com/settings/security/lesssecureapps
		//   logged in to your gmail account
		// 3. fromAddress to be your own address for the gmail account you are using
		// 4. config in Config.groovy grails{ mail{: username/password 
		def fromAddress = "hakan.dahl.demo1@gmail.com"
		def subjectField = "AP TEST subject"
		def bodyPlainText = "AP TEST body"

		mailingService.send(fromAddress, subjectField, bodyPlainText)
	}
}
