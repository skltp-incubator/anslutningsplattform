package se.skltp.av.service

import grails.transaction.Transactional

@Transactional
class TakService {
	
	TakServiceImpl takServiceImpl = new TakServiceImpl()

    def getAllTjanstekontrakt(String tjansteDoman) {
		if(tjansteDoman){
			return takServiceImpl.getAllTjanstekontrakt(tjansteDoman)
		}
		return takServiceImpl.getAllTjanstekontrakt()
    }
	
	def getAllProducentAnslutningar(String id){
		takServiceImpl.getAllProducentAnslutningar(id)
	}
}
