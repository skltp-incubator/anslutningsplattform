package se.skltp.av.api

import se.skltp.av.ProducentBestallning
import grails.rest.RestfulController
import se.skltp.av.services.dto.ProducentBestallningDTO

class ProducentBestallningApiController extends RestfulController{

	static namespace = 'v1'

	static responseFormats = ['json', 'xml']

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
}
