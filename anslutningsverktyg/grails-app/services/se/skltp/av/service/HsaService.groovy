package se.skltp.av.service

import se.skltp.av.services.dto.HsaDTO;
import grails.transaction.Transactional

@Transactional
class HsaService {

    def getHsaInformation(String searchParams) {
		
		HsaDTO hsa123 = new HsaDTO(hsaId: 'HSASERVICES-123Q')
		HsaDTO hsa456 = new HsaDTO(hsaId: 'HSASERVICES-456Q')
		
		return [hsa213, hsa456]
    }
}
