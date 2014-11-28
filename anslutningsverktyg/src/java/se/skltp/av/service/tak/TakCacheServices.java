package se.skltp.av.service.tak;

import java.util.Date;
import java.util.List;

import se.skltp.av.service.tak.m.AnropsBehorighetDTO;
import se.skltp.av.service.tak.m.TjanstekontraktDTO;
import se.skltp.av.service.tak.m.VirtualiseringDTO;
/**
 * Slim face for read operations against TAK-cache.
 */
public interface TakCacheServices {
	/**
	 * Get Endpoint connected to current instance.
	 * @return URL
	 */
	public String getEndpoint();
	
	/**
	 * Get Id for current instance.
	 * @return current instance id.
	 */
	public String getId();
	
	/**
	 * Date reference to when instance endpoint was last to cache.
	 * @return
	 */
	public Date lastSynched();
	
	/**
	 * Get DTO representation of cached values.
	 * @return
	 */
	public List<AnropsBehorighetDTO> getAllAnropsBehorigheter();
	
	/**
	 * Get DTO representation of cached values.
	 * @return
	 */
	public List<TjanstekontraktDTO> getAllTjanstekontrakt();
	
	/**
	 * Get DTO represensation of cached values.
	 * @return
	 */
	public List<VirtualiseringDTO> getAllVirtualiseringar();
}
