package se.skltp.av.service

import grails.transaction.Transactional

@Transactional
class TakService {
	
	TakServiceImpl takServiceImpl = new TakServiceImpl()

    def getAllTjanstekontrakt() {
		takServiceImpl.getAllTjanstekontrakt()
    }
	
	def getAllProducentAnslutningar(String id){
		takServiceImpl.getAllProducentAnslutningar(id)
	}
}
