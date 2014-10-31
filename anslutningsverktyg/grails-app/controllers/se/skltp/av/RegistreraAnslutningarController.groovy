package se.skltp.av

import grails.transaction.Transactional;

class RegistreraAnslutningarController {

	
    def show(ProducentBestallning producentBestallningInstance) {		
		render(view: 'index')
		respond producentBestallningInstance
	}
	
	@Transactional
	def save(){
		
		def producentBestallningInstance = ProducentBestallning.get(params.producentBestallningId)
			
		if (producentBestallningInstance == null) {
			notFound()
			return
		}
		
		producentBestallningInstance.save flush:true
			
		redirect(controller: "producentBestallning", action: "show", id: producentBestallningInstance.id, params: [producentBestallningInstance: producentBestallningInstance])
	}
	
	@Transactional
	def update(){
		
		def producentBestallningInstance = ProducentBestallning.get(params.producentBestallningId)
			
		if (producentBestallningInstance == null) {
			notFound()
			return
		}
		
		println params
		
		producentBestallningInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ProducentBestallning.label', default: 'ProducentBestallning'), producentBestallningInstance])
                redirect(controller: "registreraAnslutningar", action: "show", id: producentBestallningInstance.id, params: [producentBestallningInstance: producentBestallningInstance])
            }
            '*'{ respond producentBestallningInstance, [status: OK] }
        }
	}
}
