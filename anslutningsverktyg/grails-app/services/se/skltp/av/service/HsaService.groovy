package se.skltp.av.service

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

import se.skl.tp.hsa.cache.HsaCache;
import se.skl.tp.hsa.cache.HsaCacheImpl;
import se.skl.tp.hsa.cache.HsaNodeInfo;
import se.skltp.av.services.dto.HsaDTO;
import se.skltp.av.services.dto.TjanstekontraktDTO;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HsaService {
	
	def grailsApplication
	
	private HsaCache hsaCache

    def freeTextSearch(String searchText, int maxNoOfHits) {
		
		def resultFromCache = getHsaCache().freeTextSearch(searchText, maxNoOfHits);
		
		def result = []
		resultFromCache.each{
			result.add(new HsaDTO(hsaId: it.hsaId, dn: it.dn))
		}
		
		return result;
    }
	
	def getHsaCache(){
		if(!hsaCache){
			def hsaFiles = grailsApplication.config.hsa.hsacache.files
			hsaCache = new HsaCacheImpl(hsaFiles as String[]);
		}
		return hsaCache
	}
	
	
}
