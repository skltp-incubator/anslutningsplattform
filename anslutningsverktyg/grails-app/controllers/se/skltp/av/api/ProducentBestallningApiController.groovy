package se.skltp.av.api

import se.skltp.av.ProducentBestallning
import grails.rest.RestfulController
import se.skltp.av.services.dto.ProducentBestallningDTO

class ProducentBestallningApiController{

	static namespace = 'v1'

	def mailingService

	def producentBestallningService

	def list() {
		log.debug params
		respond producentBestallningService.listProducentBestallning()
	}

	def get(long id) {
		log.debug params
		def producentBestallning = ProducentBestallning.get(id)

		if(ProducentBestallning == null) {
			render status:404
		}
		else {

			def producentBestallningDTO = new ProducentBestallningDTO(
					id: producentBestallning.id,
					status: producentBestallning.status,
					miljo: producentBestallning.miljo
					)

			return [producentBestallning: producentBestallningDTO]
		}
	}



	def save(ProducentBestallningDTO producentBestallningDTO) {
		
		log.debug "API, a save requested for producentbestallning: $producentBestallningDTO"

		producentBestallningService.updateProducentBestallning(producentBestallningDTO)
		
		render status:201
	}
}
