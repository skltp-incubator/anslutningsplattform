package se.skltp.av.api

import se.skltp.av.ProducentBestallning
import grails.rest.RestfulController
import se.skltp.av.services.dto.ProducentBestallningDTO

class ProducentBestallningApiController extends RestfulController{

	static allowedMethods = [save: "POST"]
	
	static namespace = 'v1'

	static responseFormats = ['json', 'xml']
	
	def mailingService

	def producentBestallningService

	ProducentBestallningApiController() {
		super(ProducentBestallningDTO)
	}

	def list() {
		respond producentBestallningService.listProducentBestallning()
	}

	def create(ProducentBestallningDTO producentBestallning) {
		log.debug producentBestallning
	}

	def update(ProducentBestallningDTO producentBestallning) {
		log.debug producentBestallning
	}

	def get(long id) {
		log.debug params
		def producentBestallning = ProducentBestallning.get(id)

		if(ProducentBestallning == null) {
			render status:404
		}
		else {

			def roducentBestallningDTO = new ProducentBestallningDTO(
					id: producentBestallning.id,
					status: producentBestallning.status,
					miljo: producentBestallning.miljo
			)

			return [producentBestallning: producentBestallningDTO]
		}

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
