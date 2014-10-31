package se.skltp.av

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional;
import se.skltp.av.service.TakService
import se.skltp.av.ProducentBestallning
import static org.springframework.http.HttpStatus.*
import se.skltp.av.services.dto.TjanstekontraktDTO
import groovy.time.TimeCategory
import java.util.Date;
import java.util.regex.Pattern

import org.apache.shiro.SecurityUtils

class RegistreraTjansteKontraktController {
	
	def pattern = ~/on/
	
	def takService

    def tjanstekontrakt = { 
		render(contentType: "application/json") { takService.getAllTjanstekontrakt() }
	}
	
	def producentAnslutningar = {
		render(contentType: "application/json") { takService.getAllProducentAnslutningar(params.id) }
	}
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def searchTjansteKontrakt(String tjansteproducent, String miljo, String tjansteDoman){
		
		def tjansteKontraktSummering = []
		
		//Fixme: Hantera redan existerande anslutningar
		for(tk in takService.getAllTjanstekontrakt(tjansteDoman)){
			tjansteKontraktSummering.add(
				new TjanstekontraktDTO(
					namnrymd: tk.getNamnrymd(),
					version: 1,
					existerarRedanForProducent: false
			))
		}
		
		render(template: "tjansteKontraktLista", model: [searchresults:  tjansteKontraktSummering])
	}
	
	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProducentBestallning.list(params), model:[producentBestallningInstanceCount: ProducentBestallning.count()]
    }

    def show(ProducentBestallning producentBestallningInstance) {
        respond producentBestallningInstance
    }

    def create() {
        respond new ProducentBestallning(params)
    }
	
	def edit(ProducentBestallning producentBestallningInstance) {
		respond producentBestallningInstance
	}
	

	@Transactional
	def save(ProducentBestallning producentBestallningInstance) {
			
		if (producentBestallningInstance == null) {
			notFound()
			return
		}

		if (producentBestallningInstance.hasErrors()) {
			respond producentBestallningInstance.errors, view:'create'
			return
		}
		
		def today = new Date()
		def hundredYearsFromToday
		
		use (TimeCategory) {
			hundredYearsFromToday = today + 100.years
		}
		
		producentBestallningInstance.save flush:true
		
		params.each {		
			if (it?.value instanceof String && it?.value?.matches(pattern)){
				
				def anslutning = new ProducentAnslutning(
					rivTaProfile: producentBestallningInstance?.defaultRivTaProfile,
					url:'https://path/to/service',
					tjansteKontrakt: it.key,
					validFromTime: today,
					validToTime: hundredYearsFromToday,
					producentBestallning: producentBestallningInstance
					)
				
				producentBestallningInstance.addToProducentAnslutning(anslutning)
			}
		}
					
		
		
		//Create history post for the save
		new BestallningsHistorik(
			status: Status.UPPDATERAD,
			datum: new Date(),
			producentBestallning: producentBestallningInstance,
			senastUppdateradAv: SecurityUtils.subject.principal
			).save()
			
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'producentBestallning.label', default: 'RegistreraTjansteKontrakt'), producentBestallningInstance.id])
				//redirect producentBestallningInstance
				redirect(controller: "registreraLogiskAdress", action: "show", id: producentBestallningInstance.id, params: [producentBestallningInstance: producentBestallningInstance])
			}
			'*' { respond producentBestallningInstance, [status: CREATED], view:'show' }
		}
	}

	@Transactional
	def update(ProducentBestallning producentBestallningInstance) {
		
		println "spara update"
		
		if (producentBestallningInstance == null) {
			notFound()
			return
		}

		if (producentBestallningInstance.hasErrors()) {
			respond producentBestallningInstance.errors, controller: "registreraTjansteKontrakt", view:'edit'
			return
		}

		def today = new Date()
		def hundredYearsFromToday
		
		use (TimeCategory) {
			hundredYearsFromToday = today + 100.years
		}
		
		producentBestallningInstance.save flush:true
		
		params.each {		
			if (it?.value instanceof String && it?.value?.matches(pattern)){
				
				def anslutning = new ProducentAnslutning(
					rivTaProfile: producentBestallningInstance?.defaultRivTaProfile,
					url:'https://path/to/service',
					tjansteKontrakt: it.key,
					validFromTime: today,
					validToTime: hundredYearsFromToday,
					producentBestallning: producentBestallningInstance
					)
				
				producentBestallningInstance.addToProducentAnslutning(anslutning)
			}
		}
		
		//Create history post for the update
		new BestallningsHistorik(
			status: Status.UPPDATERAD,
			datum: new Date(),
			producentBestallning: producentBestallningInstance,
			senastUppdateradAv: SecurityUtils.subject.principal
			).save()

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'ProducentBestallning.label', default: 'RegistreraTjansteKontrakt'), producentBestallningInstance.id])
				redirect producentBestallningInstance
				redirect(controller: "registreraTjansteKontrakt", action: "edit", id: producentBestallningInstance.id, params: [producentBestallningInstance: producentBestallningInstance])
			}
			'*'{ respond producentBestallningInstance, [status: OK] }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'producentBestallning.label', default: 'RegistreraTjansteKontrakt'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
