package se.skltp.av.service

import se.skltp.av.services.dto.TjanstekontraktDTO;
import grails.transaction.Transactional

@Transactional
class HsaService {
	
	HsaServiceImpl hsaServiceImpl = new HsaServiceImpl()

    def freeTextSearch(String searchParams, int maxNoOfHits) {
			
		return hsaServiceImpl.freeTextSearch(searchParams, maxNoOfHits);
    }
	
	
}
