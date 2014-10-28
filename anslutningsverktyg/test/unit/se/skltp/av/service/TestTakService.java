package se.skltp.av.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestTakService {

	private static TakServiceImpl service = null;

	@BeforeClass
	static public void setup() {
		service = new TakServiceImpl();
	}

    @Test
    public void testGetAllTjanstekontrakt() {
    	// Not yet impl in RIVTA-box, therefore expect an error for the time being...
    	String errMsg = "javax.xml.ws.WebServiceException: Could not find wsdl:binding operation info for web method hamtaAllaTjanstekontrakt.";
    	try {
    		@SuppressWarnings("unused")
			List<Tjanstekontrakt> response = service.getAllTjanstekontrakt();
    		fail("Expected error: " + errMsg);
    	} catch (Exception e) {
    		assertEquals(errMsg, e.toString());
    	}
//        assertEquals(0, response.size());
    }

    @Test
    public void testgetAllProducentAnslutningarFullId() {
    	List<ProducentAnslutning> response = service.getAllProducentAnslutningar("5565594230");
    	assertEquals(6, response.size());
    }

    @Test
    public void testgetAllProducentAnslutningarPartialId() {
    	List<ProducentAnslutning> response = service.getAllProducentAnslutningar("HSA-V");
    	assertEquals(3, response.size());
    }
}