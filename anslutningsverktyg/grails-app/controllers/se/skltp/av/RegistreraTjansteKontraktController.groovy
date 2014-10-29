package se.skltp.av

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional;
import se.skltp.av.service.TakService
import se.skltp.av.ProducentBestallning
import static org.springframework.http.HttpStatus.*
import se.skltp.av.services.dto.TjanstekontraktDTO

import org.apache.shiro.SecurityUtils

class RegistreraTjansteKontraktController {
	
	def takService

    def tjanstekontrakt = { 
		render(contentType: "application/json") { takService.getAllTjanstekontrakt() }
	}
	
	def producentAnslutningar = {
		render(contentType: "application/json") { takService.getAllProducentAnslutningar(params.id) }
	}
	
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	enum Status {
		NY, UPPDATERAD
	}
	
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
		
		println "Spara"
		
		if (producentBestallningInstance == null) {
			notFound()
			return
		}

		if (producentBestallningInstance.hasErrors()) {
			respond producentBestallningInstance.errors, view:'create'
			return
		}
					
		producentBestallningInstance.save flush:true
		
		//Create history post for the save
		new BestallningsHistorik(
			status: Status.NY,
			datum: new Date(),
			producentBestallning: producentBestallningInstance,
			senastUppdateradAv: SecurityUtils.subject.principal
			).save()


		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'producentBestallning.label', default: 'RegistreraTjansteKontrakt'), producentBestallningInstance.id])
				redirect producentBestallningInstance
			}
			'*' { respond producentBestallningInstance, [status: CREATED], view:'show' }
		}
	}

	@Transactional
	def update(ProducentBestallning producentBestallningInstance) {
		if (producentBestallningInstance == null) {
			notFound()
			return
		}

		if (producentBestallningInstance.hasErrors()) {
			respond producentBestallningInstance.errors, view:'edit'
			return
		}

		producentBestallningInstance.save flush:true
		
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
			}
			'*'{ respond producentBestallningInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(ProducentBestallning producentBestallningInstance) {

		if (producentBestallningInstance == null) {
			notFound()
			return
		}
		
		//Delete history and connections
		
		

		producentBestallningInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'ProducentBestallning.label', default: 'RegistreraTjansteKontrakt'), producentBestallningInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
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
