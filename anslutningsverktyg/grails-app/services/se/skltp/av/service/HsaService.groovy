package se.skltp.av.service

import se.skltp.av.services.dto.HsaDTO;
import grails.transaction.Transactional

@Transactional
class HsaService {
	
	HsaServiceImpl hsaServiceImpl = new HsaServiceImpl()

    def getHsaInformation(String searchParams) {
			
		return hsaServiceImpl.freeTextSearch(searchParams,-1);
    }
	
	
}
