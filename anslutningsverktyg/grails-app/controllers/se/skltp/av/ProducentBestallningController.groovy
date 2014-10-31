package se.skltp.av



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import org.apache.shiro.SecurityUtils

@Transactional(readOnly = true)
class ProducentBestallningController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProducentBestallning.list(params), model:[producentBestallningInstanceCount: ProducentBestallning.count()]
    }

    def show(ProducentBestallning producentBestallningInstance) {
        respond producentBestallningInstance
    }
	
	@Transactional
	def confirm(ProducentBestallning producentBestallningInstance) {
		
		//Dummy confirm
		
		//Create history post for the save
		new BestallningsHistorik(
			status: Status.NY,
			datum: new Date(),
			producentBestallning: producentBestallningInstance,
			senastUppdateradAv: SecurityUtils.subject.principal
			).save()

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'Beställningen bekräftad', args: [message(code: 'ProducentBestallning.label', default: 'ProducentBestallning'), producentBestallningInstance.id])
				redirect(controller: "producentBestallning", action: "show", id: producentBestallningInstance.id, params: [producentBestallningInstance: producentBestallningInstance])
			}
			'*' { respond producentBestallningInstance, [status: OK], view:'show' }
		}
	}

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'producentBestallning.label', default: 'ProducentBestallning'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
