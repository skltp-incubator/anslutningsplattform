package se.skltp.av.services.dto

import grails.validation.Validateable
import groovy.transform.ToString
import se.skltp.av.services.dto.TjansteKomponentDTO
import se.skltp.av.services.dto.DriftMiljoDTO
import se.skltp.av.util.BestallningsStatus;

@Validateable
@ToString
class ProducentBestallningDTO {
	
	long id
	
	boolean slaFullfilled
	String otherInfo
	BestallningsStatus status
	
	TjansteDomanDTO serviceDomain
    TjansteKomponentDTO serviceComponent
	TjansteKomponentDTO serviceConsumer
    DriftMiljoDTO targetEnvironment

	AnsvarigDTO client
	List<TjansteKontraktDTO> serviceContracts
}