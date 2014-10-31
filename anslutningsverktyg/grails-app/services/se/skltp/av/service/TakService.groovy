package se.skltp.av.service

import grails.transaction.Transactional

@Transactional
class TakService {
	
	def grailsApplication
	
	def takServiceImpl

    def getAllTjanstekontrakt(String tjansteDoman) {
		if(tjansteDoman){
			return getTakServiceImpl().getAllTjanstekontrakt(tjansteDoman)
		}
		return getTakServiceImpl().getAllTjanstekontrakt()
    }
	
	def getAllProducentAnslutningar(String id){
		getTakServiceImpl().getAllProducentAnslutningar(id)
	}
	
	def getTakServiceImpl(){
		if(!takServiceImpl){
			def takUrl = grailsApplication.config.tak.sokvagvalsinfo.url
			takServiceImpl = new TakServiceImpl(takUrl)
		}
		return takServiceImpl
	}
}
