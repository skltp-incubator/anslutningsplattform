package se.skltp.av.service.tak;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.skltp.av.service.tak.m.AnropsBehorighetDTO;
import se.skltp.av.service.tak.m.TjanstekontraktDTO;
import se.skltp.av.service.tak.m.VirtualiseringDTO;

public class TakCacheServicesImpl implements TakCacheServices {
	private static final Logger log = LoggerFactory.getLogger(TakCacheServicesImpl.class);
	
	private final String endpoint;
	private final String id;
	private final String filePath;
	
	public TakCacheServicesImpl(final String endpoint, final String id, final String filePath) {
		this.endpoint = endpoint;
		this.id = id;
		this.filePath = filePath;
	}
	
	/**
	 * API
	 */	
	public String getEndpoint() {
		return this.endpoint;
	}
	
	public String getFilePath() {
		return this.filePath;
	}
	
	public String getId() {
		return this.id;
	}
	
	public Date lastSynched() {
		return TakCache.lastSynched(this.endpoint);
	}

	@Override
	public List<AnropsBehorighetDTO> getAllAnropsBehorigheter() {
		return TakCache.getAnropsBehorighet(this.endpoint);
	}

	@Override
	public List<TjanstekontraktDTO> getAllTjanstekontrakt() {
		return TakCache.getTjanstekontrakt(this.endpoint);
	}

	@Override
	public List<VirtualiseringDTO> getAllVirtualiseringar() {
		return TakCache.getVirtualisering(this.endpoint);
	}
	

}
