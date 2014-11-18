package se.skltp.av.api

import grails.rest.RestfulController;
import se.skltp.av.TjansteKomponent;

class TjansteKomponentApiController extends RestfulController{

	def tjansteKomponentService

	TjansteKomponentApiController() {
		super(TjansteKomponent)
	}

	def query() {
		println "$params"
		//Bara hämta namn och id
		def tjansteKomponenter = tjansteKomponentService.query(params.query)
		respond tjansteKomponenter
	}
}
