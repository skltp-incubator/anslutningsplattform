package se.skltp.av

import grails.converters.JSON
import grails.converters.XML
import se.skltp.av.service.HsaService
import java.util.regex.Pattern

class RegistreraLogiskAdressController {

	def hsaService
	def maxNoOfHits = 100
	def pattern = ~/on/

    def hsaInformation = { 
		render(contentType: "application/json") { hsaService.freeTextSearch(params.id, -1) }
	}
	
	def freetextSearch(String query){
		render(template: "searchResults", model: [searchresults: hsaService.freeTextSearch(query, maxNoOfHits)])
	}
	
	def show(){
		render(view: 'freetextSearch')
	}
	
	def save(){
		
		params.each {
			if (it?.value?.matches(pattern)){
				println it.key
			}
		}
	
		//Till nästa sida vid ok
		println "Till nästa sida"
		
		forward(controller:"bekraftaProducentBestallning",action:"index")
		
	}
	
}
