package se.skltp.av.service.tak;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import se.skltp.tak.vagvalsinfo.wsdl.v2.SokVagvalsInfoInterface;

public class TakCacheImpl implements TakCache {
	
	private static final ConcurrentHashMap<String, SokVagvalsInfoInterface> clientCache;
	
	static {
		clientCache = new ConcurrentHashMap<String, SokVagvalsInfoInterface>();
	}

	public void put(final Object obj) {
		// TODO Auto-generated method stub
		
	}

	public Object get(final Object obj) {
		return null;
	}
	
	public Date lastSynched() {
		return new Date();
	}
	
	public String test() {
		return "THIS IS A TEST STRING";
	}
	
	public SokVagvalsInfoInterface createClient(final String endpoint) {
		if(clientCache.containsKey(endpoint)) {
			return clientCache.get(endpoint);
		}
		final JaxWsProxyFactoryBean jaxWs = new JaxWsProxyFactoryBean();
		jaxWs.setServiceClass(SokVagvalsInfoInterface.class);
		jaxWs.setAddress(endpoint);
		final SokVagvalsInfoInterface client = (SokVagvalsInfoInterface) jaxWs.create();
		return clientCache.putIfAbsent(endpoint, client);
	}

}
