package se.skltp.av.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.skltp.tk.vagvalsinfo.wsdl.v2.SokVagvalsInfoInterface;
import se.skltp.tk.vagvalsinfo.wsdl.v2.SokVagvalsServiceSoap11LitDocService;
import se.skltp.tk.vagvalsinfo.wsdl.v2.TjanstekontraktInfoType;
import se.skltp.tk.vagvalsinfo.wsdl.v2.VirtualiseringsInfoType;

public class TakServiceImpl {

	private Logger log = LoggerFactory.getLogger(TakServiceImpl.class);
	private SokVagvalsInfoInterface webServiceProxy;
	
	public TakServiceImpl() {
		
		// FIXME: To be placed in configuration files
		String url = "http://33.33.33.33:8080/tp-vagval-admin-services/SokVagvalsInfo/v2?wsdl";
		
		log.info("Creates a TakService based on the TAK-URL: {}", url);
		try {
			webServiceProxy = new SokVagvalsServiceSoap11LitDocService(new URL(url)).getSokVagvalsSoap11LitDocPort();
		} catch (MalformedURLException e) {
			log.error("Failed to create proxy to TAK web service: " + url, e);
			throw new RuntimeException(e);
		}
	}
	
	public List<Tjanstekontrakt> getAllTjanstekontrakt() {
		List<TjanstekontraktInfoType> response = webServiceProxy.hamtaAllaTjanstekontrakt(null).getTjanstekontraktInfo();

		List<Tjanstekontrakt> result = new ArrayList<>(); 
		
		if (response == null) return result;
		
		for (final TjanstekontraktInfoType tk: response) {
			result.add(new Tjanstekontrakt() {
				public String getNamnrymd()    {return tk.getNamnrymd();}
				public String getVersion()     {return tk.getVersion();}
				public String getBeskrivning() {return tk.getBeskrivning();}
			});
		}
		return result;
	}
	
	public List<ProducentAnslutning> getAllProducentAnslutningar(String id) {
		
		List<ProducentAnslutning> result = new ArrayList<>(); 

		// Return empty result of no id is specified
		if (id == null || id.length() == 0) return result;

		List<VirtualiseringsInfoType> response = webServiceProxy.hamtaAllaVirtualiseringar(null).getVirtualiseringsInfo();
		
		// Return empty result of no result is found
		if (response == null) return result;
		
		for (final VirtualiseringsInfoType pa: response) {
			// FIXME: Fix for the incorrect data model in TAK. Accept all records that has a receiverId that starts with the given producer id 
			if (pa.getReceiverId().toUpperCase().startsWith(id)) {
				result.add(new ProducentAnslutning() {
					public String getUrl() {return pa.getAdress();}
					public String getRivProfil() {return pa.getRivProfil();}
					public String getTjansteKontrakt() {return pa.getTjansteKontrakt();}
					public XMLGregorianCalendar getFromTidpunkt() {return pa.getFromTidpunkt();}
					public XMLGregorianCalendar getTomTidpunkt() {return pa.getTomTidpunkt();}
				});
			}
		}
		return result;
	}
}
