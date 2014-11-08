package se.skltp.av.api

import se.skltp.av.ProducentBestallning;
import grails.rest.RestfulController

class ProducentBestallningApiController extends RestfulController{

	static namespace = 'v1'

	static responseFormats = ['json', 'xml']

	ProducentBestallningApiController() {
		super(ProducentBestallning)
	}
}
