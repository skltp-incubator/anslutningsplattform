package se.skltp.av.service

import javax.annotation.PostConstruct;
import se.skltp.av.services.dto.TakRoutingEntryDTO
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TakService {
	
	def grailsApplication
	
	//def takServiceImpl
	
	def takRoutingMap
	def takCacheMap

	// don't do lazy init, we want to make sure that TAK-caches are populated
	// and available at startup to make sure config is ok
	boolean lazyInit = false
	
	@PostConstruct
	def init() {
		log.debug("init TAK routing from config ...")
		def confMap = grailsApplication.getFlatConfig()
		takRoutingMap = getTakRoutingMap(confMap)
		log.debug("done init TAK routing from config")

		log.debug("init TAK caches ...")
		takCacheMap = new HashMap()
		takRoutingMap.values().each {
			takCacheMap.putAt(it.id, getTakCache(it.id, it.url))
		}		
		log.debug("done init TAK caches")
	}
	
	/**
	 * Read TAK routing config from configuration, expects config in
	 * conf/Config.groovy like:
	 * <pre>
	 * tak.env.id.'1' = 'ntjp-prod'
	 * tak.env.name.'1' = 'NTjP PROD'
	 * tak.env.url.'1' = 'http://TODO-PROD'
	 * 
	 * tak.env.id.'2' = 'ntjp-qa'
	 * tak.env.name.'2' = 'NTjP QA'
	 * tak.env.url.'2' = 'http://TODO-QA'
	 * 
	 * tak.env.id.'3' = 'ntjp-test'
	 * tak.env.name.'3' = 'NTjP TEST'
	 * tak.env.url.'3' = 'http://TODO-TEST'
	 * </pre>
	 * 
	 * @param confMap
	 * @return
	 */
	def getTakRoutingMap(confMap) {
		def confTakMap = new HashMap()
		
		// populate map from configuration properties
		confMap.keySet().each {
			if (it.startsWith('tak.env.')) {
				log.debug("found TAK property: " + it + " = " + confMap.get(it))
				def takEnvNr = it.substring(it.lastIndexOf('.') + 1, it.length())
				
				TakRoutingEntryDTO dto = confTakMap.get(takEnvNr)
				if (dto == null) {
					dto = new TakRoutingEntryDTO()
					confTakMap.put(takEnvNr, dto)
				}
				
				if (it.startsWith('tak.env.id.')) {
					dto.id = confMap.get(it)
				}
				else if (it.startsWith('tak.env.name.')) {
					dto.name = confMap.get(it)
				}
				else if (it.startsWith('tak.env.url.')) {
					dto.url = confMap.get(it)
				}
				else {
					throw new IllegalArgumentException("Bad TAK-configuration, property name format unknown: " + it)
				}				
			}
		}
		
		// validate entries in map, check that all values are initialized/non-null
		confTakMap.keySet().each {
			def dto = confTakMap.get(it)
			if (dto.id == null || dto.name == null || dto.url == null) {
				throw new IllegalArgumentException("Bad TAK-configuration, a property is null for env nr: " + it
					+ ", id: " + id + ", name: " + name + ", url: " + url) 
			}
		}
		
		// create a lookup map with id as key (now that we have caught all config)
		def takMap = new HashMap()
		confTakMap.values().each {
			takMap.put(it.id, it)
			log.debug("TAK routing map entry: key: " + it.id + ", value: " + it)
		}
		
		takMap
	}
	
	def getTakCache(String id, String url) {
		"TODO: init real TAK-cache for: id: $id, url: $url"		
	}
	
	def getTakRoutingEntriesList() {
		new ArrayList(takRoutingMap.values())
	}

// TODO
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
