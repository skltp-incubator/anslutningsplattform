package se.skltp.av

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional;
import se.skltp.av.service.HsaService

import java.util.regex.Pattern

class RegistreraLogiskAdressController {

	def hsaService
	def maxNoOfHits = 100
	def pattern = ~/on/
	
	def freetextSearch(String query){
		render(template: "searchResults", model: [searchresults: hsaService.freeTextSearch(query, maxNoOfHits)])
	}
	
	def show(ProducentBestallning producentBestallningInstance) {	
		render(view: 'freetextSearch')
		respond producentBestallningInstance
	}
	
	@Transactional
	def save(){
		
		def producentBestallningInstance = ProducentBestallning.get(params.producentBestallningId)
			
		if (producentBestallningInstance == null) {
			notFound()
			return
		}
		
		params.each {
			if (it?.value?.matches(pattern)){
					
				def logiskAdress = LogiskAdress.findByHsaId(it.key)
				
				if(!logiskAdress){
					logiskAdress = new LogiskAdress(hsaId: it.key)
				}
				 
				producentBestallningInstance.addToDefaultLogiskAdress(logiskAdress)
				
				producentBestallningInstance.producentAnslutning.each {anslutning ->
					anslutning.addToLogiskAdresser(logiskAdress)
				}
			}
		}
			
		producentBestallningInstance.save(flush:true, failOnError: true)
		
		redirect(controller: "registreraAnslutningar", action: "show", id: producentBestallningInstance.id, params: [producentBestallningInstance: producentBestallningInstance])
			
		
	}
	
	@Transactional
	def update(){
		
		def producentBestallningInstance = ProducentBestallning.get(params.producentBestallningId)
			
		if (producentBestallningInstance == null) {
			notFound()
			return
		}

		params.each {
			if (it?.value?.matches(pattern)){
					
				def logiskAdress = LogiskAdress.findByHsaId(it.key)
				
				if(!logiskAdress){
					logiskAdress = new LogiskAdress(hsaId: it.key)
				}
				 
				producentBestallningInstance.addToDefaultLogiskAdress(logiskAdress)
				
				producentBestallningInstance.producentAnslutning.each {anslutning ->
					anslutning.addToLogiskAdresser(logiskAdress)
				}
			}
		}	
			
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'ProducentBestallning.label', default: 'ProducentBestallning'), producentBestallningInstance])
				redirect(controller: "registreraLogiskAdress", action: "show", id: producentBestallningInstance.id, params: [producentBestallningInstance: producentBestallningInstance])
			}
			'*'{ respond producentBestallningInstance, [status: OK] }
		}
		
	}
}
