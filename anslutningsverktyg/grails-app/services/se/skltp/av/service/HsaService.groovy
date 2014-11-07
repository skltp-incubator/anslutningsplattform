package se.skltp.av.service

import se.skltp.av.services.dto.TjanstekontraktDTO;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HsaService {
	
	def grailsApplication
	
	def hsaServiceImpl

    def freeTextSearch(String searchParams, int maxNoOfHits) {
		return getHsaService().freeTextSearch(searchParams, maxNoOfHits);
    }
	
	def getHsaService(){
		if(!hsaServiceImpl){
			def hsaFiles = grailsApplication.config.hsa.hsacache.files
			hsaServiceImpl = new HsaServiceImpl(hsaFiles as String[])
		}
		return hsaServiceImpl
	}
	
	
}
