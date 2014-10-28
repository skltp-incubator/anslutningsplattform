package se.skltp.av



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.apache.shiro.SecurityUtils

@Transactional(readOnly = true)
class ProducentBestallningController {
	
	static scaffold = true

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	enum Status {
		NY, UPPDATERAD
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
                flash.message = message(code: 'default.created.message', args: [message(code: 'producentBestallning.label', default: 'ProducentBestallning'), producentBestallningInstance.id])
                redirect producentBestallningInstance
            }
            '*' { respond producentBestallningInstance, [status: CREATED] }
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ProducentBestallning.label', default: 'ProducentBestallning'), producentBestallningInstance.id])
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
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ProducentBestallning.label', default: 'ProducentBestallning'), producentBestallningInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
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
