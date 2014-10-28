package se.skltp.av



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import se.skltp.av.service.HsaService

@Transactional(readOnly = true)
class ProducentAnslutningController {
	
	def hsaService
	
	static scaffold = true

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Transactional
    def save(ProducentAnslutning producentAnslutningInstance) {
        if (producentAnslutningInstance == null) {
            notFound()
            return
        }

        if (producentAnslutningInstance.hasErrors()) {
            respond producentAnslutningInstance.errors, view:'create'
            return
        }

        producentAnslutningInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'producentAnslutning.label', default: 'ProducentAnslutning'), producentAnslutningInstance.id])
                redirect producentAnslutningInstance
            }
            '*' { respond producentAnslutningInstance, [status: CREATED] }
        }
    }

    @Transactional
    def update(ProducentAnslutning producentAnslutningInstance) {
        if (producentAnslutningInstance == null) {
            notFound()
            return
        }

        if (producentAnslutningInstance.hasErrors()) {
            respond producentAnslutningInstance.errors, view:'edit'
            return
        }

        producentAnslutningInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ProducentAnslutning.label', default: 'ProducentAnslutning'), producentAnslutningInstance.id])
                redirect producentAnslutningInstance
            }
            '*'{ respond producentAnslutningInstance, [status: OK] }
        }
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'producentAnslutning.label', default: 'ProducentAnslutning'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
