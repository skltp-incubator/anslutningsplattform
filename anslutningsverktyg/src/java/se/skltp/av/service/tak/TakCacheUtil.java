package se.skltp.av.service.tak;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import se.skltp.av.service.tak.m.AnropsBehorighetDTO;
import se.skltp.av.service.tak.m.TjanstekontraktDTO;
import se.skltp.av.service.tak.m.VirtualiseringDTO;
import se.skltp.tak.vagvalsinfo.wsdl.v2.AnropsBehorighetsInfoIdType;
import se.skltp.tak.vagvalsinfo.wsdl.v2.AnropsBehorighetsInfoType;
import se.skltp.tak.vagvalsinfo.wsdl.v2.TjanstekontraktInfoType;
import se.skltp.tak.vagvalsinfo.wsdl.v2.VirtualiseringsInfoIdType;
import se.skltp.tak.vagvalsinfo.wsdl.v2.VirtualiseringsInfoType;

public final class TakCacheUtil {
	
	public static boolean equals(final Date dateOne, final Date dateTwo) {
		if(dateOne == null && dateTwo == null) {
			return true;
		}
		if(dateOne != null && dateTwo != null) {
			if(dateOne.getTime() == dateTwo.getTime()) {
				return true;
			}
		}
		return false;
	}
	
	public static VirtualiseringDTO map(final VirtualiseringsInfoType type) {
		if(type == null) {
			return null;
		}
		return new VirtualiseringDTO(
				type.getAdress(), 
				toDate(type.getFromTidpunkt()), 
				type.getReceiverId(), 
				type.getRivProfil(), 
				type.getTjansteKontrakt(), 
				toDate(type.getTomTidpunkt()), 
				getId(type.getVirtualiseringsInfoId()));
	}
	
	public static AnropsBehorighetDTO map(final AnropsBehorighetsInfoType type) {
		if(type == null) {
			return null;
		}
		return new AnropsBehorighetDTO(
				getId(type.getAnropsBehorighetsInfoId()),
				toDate(type.getFromTidpunkt()), 
				toDate(type.getTomTidpunkt()), 
				type.getReceiverId(), 
				type.getSenderId(), 
				type.getTjansteKontrakt());
	}
	
	public static TjanstekontraktDTO map(final TjanstekontraktInfoType type) {
		if(type == null) {
			return null;
		}
		return new TjanstekontraktDTO(type.getBeskrivning(), type.getMajorVersion(),type.getMinorVersion(), type.getNamnrymd());
	}
	
	public static Date toDate(final XMLGregorianCalendar date) {
		if(date == null) {
			return null;
		}
		return date.toGregorianCalendar().getTime();
	}
	
	public static String getId(final VirtualiseringsInfoIdType type) {
		if(type == null) {
			return null;
		}
		return type.getValue();
	}
	
	public static String getId(final AnropsBehorighetsInfoIdType type) {
		if(type == null) {
			return null;
		}
		return type.getValue();
	}
}
