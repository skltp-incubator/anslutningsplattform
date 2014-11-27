package se.skltp.av.service.tak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.skltp.av.service.tak.m.AnropsBehorighetDTO;
import se.skltp.av.service.tak.m.PersistenceEntity;
import se.skltp.av.service.tak.m.TjanstekontraktDTO;
import se.skltp.av.service.tak.m.VirtualiseringDTO;
import se.skltp.av.service.tak.persitence.TakCacheFilePersistenceImpl;
import se.skltp.av.service.tak.persitence.TakCachePersistenceServices;
import se.skltp.tak.vagvalsinfo.wsdl.v2.AnropsBehorighetsInfoType;
import se.skltp.tak.vagvalsinfo.wsdl.v2.SokVagvalsInfoInterface;
import se.skltp.tak.vagvalsinfo.wsdl.v2.TjanstekontraktInfoType;
import se.skltp.tak.vagvalsinfo.wsdl.v2.VirtualiseringsInfoType;

final class TakCache {
	
	private static final Logger log = LoggerFactory.getLogger(TakCache.class);
	
	private static final ExecutorService worker;
	
	private static final ConcurrentHashMap<String, ConcurrentHashMap<String, AnropsBehorighetDTO>> behorighet;
	private static final ConcurrentHashMap<String, ConcurrentHashMap<String, TjanstekontraktDTO>> tjanstekontrakt;
	private static final ConcurrentHashMap<String, ConcurrentHashMap<String, VirtualiseringDTO>> virtualisering;
	
	private static final ConcurrentHashMap<String, Date> endpointSync;
	
	static {
		worker = Executors.newSingleThreadExecutor();
		
		behorighet = new ConcurrentHashMap<String, ConcurrentHashMap<String, AnropsBehorighetDTO>>();
		tjanstekontrakt = new ConcurrentHashMap<String, ConcurrentHashMap<String, TjanstekontraktDTO>>();
		virtualisering = new ConcurrentHashMap<String, ConcurrentHashMap<String, VirtualiseringDTO>>();
		
		endpointSync = new ConcurrentHashMap<String, Date>();
	}
	
	private synchronized static void cacheAnropsBehorighet(final String endpoint, final List<AnropsBehorighetsInfoType> behorigheter) {
		if(endpoint != null) {
			final Set<String> currentKeys = new HashSet<String>();
			ConcurrentHashMap<String, AnropsBehorighetDTO> cache;
			if(!behorighet.contains(endpoint)) {
				cache = new ConcurrentHashMap<String, AnropsBehorighetDTO>();
				behorighet.put(endpoint, cache);
			} else {
				cache = behorighet.get(endpoint);
			}
			for(final AnropsBehorighetsInfoType type : behorigheter) {
				final AnropsBehorighetDTO dto = TakCacheUtil.map(type);
				currentKeys.add(dto.getId());
				if(cache.putIfAbsent(dto.getId(), dto) != null) {
					cache.replace(dto.getId(), dto);
				}
			}
			final Iterator<String> it = cache.keySet().iterator();
			while(it.hasNext()) {
				final String key = it.next();
				if(!currentKeys.contains(key)) {
					behorighet.remove(key);
				}
			}
		}
	}
	
	private synchronized static void cacheTjanstecontract(final String endpoint, final List<TjanstekontraktInfoType> kontrakt) {
		if(endpoint != null) {
			final Set<String> currentKeys = new HashSet<String>();
			ConcurrentHashMap<String, TjanstekontraktDTO> cache;
			if(!tjanstekontrakt.contains(endpoint)) {
				cache = new ConcurrentHashMap<String, TjanstekontraktDTO>();
				tjanstekontrakt.put(endpoint, cache);
			} else {
				cache = tjanstekontrakt.get(endpoint);
			}
			for(final TjanstekontraktInfoType type : kontrakt) {
				final TjanstekontraktDTO dto = TakCacheUtil.map(type);
				currentKeys.add(dto.getId());
				if(cache.putIfAbsent(dto.getId(), dto) != null) {
					cache.replace(dto.getId(), dto);
				}
			}
			final Iterator<String> it = cache.keySet().iterator();
			while(it.hasNext()) {
				final String key = it.next();
				if(!currentKeys.contains(key)) {
					tjanstekontrakt.remove(key);
				}
			}
		}
	}
	
	private synchronized static void cacheVirtualiseringar(final String endpoint, final List<VirtualiseringsInfoType> virtualiseringar) {
		if(endpoint != null) {
			final Set<String> currentKeys = new HashSet<String>();
			ConcurrentHashMap<String, VirtualiseringDTO> cache;
			if(!virtualisering.contains(endpoint)) {
				cache = new ConcurrentHashMap<String, VirtualiseringDTO>();
				virtualisering.put(endpoint, cache);
			} else {
				cache = virtualisering.get(endpoint);
			}
			for(final VirtualiseringsInfoType type : virtualiseringar) {
				final VirtualiseringDTO dto = TakCacheUtil.map(type);
				currentKeys.add(dto.getId());
				if(cache.putIfAbsent(dto.getId(), dto) != null) {
					cache.replace(dto.getId(), dto);
				}
			}
			final Iterator<String> it = cache.keySet().iterator();
			while(it.hasNext()) {
				final String key = it.next();
				if(!currentKeys.contains(key)) {
					virtualiseringar.remove(key);
				}
			}
		}
	}

	/**
	 * TODO: Dont create new Array
	 * @param endpoint
	 * @return
	 */
	protected static List<AnropsBehorighetDTO> getAnropsBehorighet(final String endpoint) {
		final ConcurrentHashMap<String, AnropsBehorighetDTO> cache = behorighet.get(endpoint);
		if(cache != null) {
			return Collections.unmodifiableList(new ArrayList<AnropsBehorighetDTO>(cache.values()));
		}
		return Collections.emptyList();
	}
	
	/**
	 * TODO: Dont create new Array
	 * @param endpoint
	 * @return
	 */
	protected static List<TjanstekontraktDTO> getTjanstekontrakt(final String endpoint) {
		final ConcurrentHashMap<String, TjanstekontraktDTO> cache = tjanstekontrakt.get(endpoint);
		if(cache != null) {
			return Collections.unmodifiableList(new ArrayList<TjanstekontraktDTO>(cache.values()));
		}
		return Collections.emptyList();
	}
	
	/**
	 * TODO: Dont create new Array
	 * @param endpoint
	 * @return
	 */
	protected static List<VirtualiseringDTO> getVirtualisering(final String endpoint) {
		final ConcurrentHashMap<String, VirtualiseringDTO> cache = virtualisering.get(endpoint);
		if(cache != null) {
			return Collections.unmodifiableList(new ArrayList<VirtualiseringDTO>(cache.values()));
		}
		return Collections.emptyList();
	}

	protected static Date lastSynched(final String endpoint) {
		return endpointSync.get(endpoint);
	}
	
	
	/**
	 * @param endpoints
	 * @param callback
	 */
	public static synchronized void sync(final List<String> endpoints, final TakCacheCallback callback, final TakCachePersistenceServices persistences) {
		worker.execute(new Runnable() {
			public void run() {
				final JaxWsProxyFactoryBean jaxWs = new JaxWsProxyFactoryBean();
				jaxWs.setServiceClass(SokVagvalsInfoInterface.class);
				final List<PersistenceEntity> persitencesEntitys = new ArrayList<PersistenceEntity>();
				for(final String endpoint : endpoints) {
					try {
						jaxWs.setAddress(endpoint);
						final SokVagvalsInfoInterface client = (SokVagvalsInfoInterface) jaxWs.create();
						
						//TODO: Add separate error handling for each interface? 
						final List<VirtualiseringsInfoType> vInfoTypes = client.hamtaAllaVirtualiseringar(null).getVirtualiseringsInfo();
						final List<AnropsBehorighetsInfoType> aInfoTypes = client.hamtaAllaAnropsBehorigheter(null).getAnropsBehorighetsInfo();
						final List<TjanstekontraktInfoType> tInfoTypes = client.hamtaAllaTjanstekontrakt(null).getTjanstekontraktInfo();
						
						cacheVirtualiseringar(endpoint, vInfoTypes);
						cacheTjanstecontract(endpoint, tInfoTypes);
						cacheAnropsBehorighet(endpoint, aInfoTypes);
						
						final Date synched = new Date();
						
						if(endpointSync.putIfAbsent(endpoint, synched) != null) {
							endpointSync.replace(endpoint, synched);
						}
						
						persitencesEntitys.add(new PersistenceEntity(endpoint, synched, vInfoTypes, tInfoTypes, aInfoTypes));
						
						callback.onSuccess(endpoint);
					} catch (Exception err) {
						callback.onError(endpoint, err);
					}
				}
				persistences.persistEndpoints(persitencesEntitys);
			}
		});
	}
	
	public static synchronized void loadPersistedCache(final TakCacheCallback callback, final TakCachePersistenceServices persitence) {
		worker.execute(new Runnable() {
			public void run() {
				for(PersistenceEntity entity : persitence.getEndpoints()) {
					final String endpoint = entity.getEndpoint();
					try {
						cacheAnropsBehorighet(endpoint, entity.getAnropsbehorighet());
						cacheTjanstecontract(endpoint, entity.getTjanstekontrakt());
						cacheVirtualiseringar(endpoint, entity.getVirtualiseringar());
						callback.onSuccess(endpoint);
					} catch (Exception err) {
						callback.onError(endpoint, err);
					}
				}
			}
		});
	}
}
