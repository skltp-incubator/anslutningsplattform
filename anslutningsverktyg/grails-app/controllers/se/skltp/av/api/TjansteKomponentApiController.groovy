package se.skltp.av.api

import grails.rest.RestfulController;
import se.skltp.av.TjansteKomponent;

class TjansteKomponentApiController extends RestfulController{

	static namespace = 'v1'

	static responseFormats = ['json', 'xml']

	TjansteKomponentApiController() {
		super(TjansteKomponent)
	}
}
