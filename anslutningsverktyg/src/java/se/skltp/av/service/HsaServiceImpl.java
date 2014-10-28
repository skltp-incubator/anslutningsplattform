package se.skltp.av.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.skl.tp.hsa.cache.HsaCache;
import se.skl.tp.hsa.cache.HsaCacheImpl;
import se.skl.tp.hsa.cache.HsaNodeInfo;

public class HsaServiceImpl {

	private Logger log = LoggerFactory.getLogger(HsaServiceImpl.class);
	private HsaCache hsaCache;
	
	public HsaServiceImpl() {
		
		// FIXME: To be placed in configuration files
		String dir = "grails-app/conf/";
		String[] hsaFiles = new String[] {dir + "hsacache.xml", dir + "hsacachecomplementary.xml"};
		
		log.info("Creates a HsaService based on the files: {}",StringUtils.join(hsaFiles, ", "));
		hsaCache = new HsaCacheImpl(hsaFiles);
	}
	
	public List<HsaNode> freeTextSearch(String searchText, int maxNoOfHits) {
		List<HsaNodeInfo> resultFromCache = hsaCache.freeTextSearch(searchText, maxNoOfHits);
		List<HsaNode> result = new ArrayList<>(); 
		
		if (resultFromCache == null) return result;
		
		for (final HsaNodeInfo node: resultFromCache) {
			result.add(new HsaNode() {
				public String getHsaId() {return node.getHsaId();}
				public String getDn()    {return node.getDn();}
			});
		}
		return result;
	}
}
