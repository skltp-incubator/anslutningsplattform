package se.skltp.av.service.tak;

import java.util.Date;
import java.util.List;

import se.skltp.av.service.tak.m.AnropsBehorighetDTO;
import se.skltp.av.service.tak.m.TjanstekontraktDTO;
import se.skltp.av.service.tak.m.VirtualiseringDTO;

public interface TakCacheServices {
	public String getEndpoint();
	public String getFilePath();
	public String getId();
	public Date lastSynched();
	public List<AnropsBehorighetDTO> getAllAnropsBehorigheter();
	public List<TjanstekontraktDTO> getAllTjanstekontrakt();
	public List<VirtualiseringDTO> getAllVirtualiseringar();
}
