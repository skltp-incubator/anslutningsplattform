package se.skltp.av

import grails.converters.JSON
import grails.converters.XML
import se.skltp.av.service.HsaService

class HsaController {

   def hsaService

    def hsaInformation = { 
		render(contentType: "application/json") { hsaService.getHsaInformation(params.id) }
	}
}
