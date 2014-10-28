package se.skltp.av

import grails.converters.JSON
import grails.converters.XML
import se.skltp.av.service.TakService

class TakController {
	
	def takService

    def tjanstekontrakt = { 
		render(contentType: "application/json") { takService.getAllTjanstekontrakt() }
	}
	
	def producentAnslutningar = {
		render(contentType: "application/json") { takService.getAllProducentAnslutningar(params.id) }
	}
}
