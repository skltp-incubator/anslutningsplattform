

package se.skltp.av.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

@TestFor(HsaService)
class HsaServiceTestNew{
	
	def hsaService

	@Test
    public void testSearchNoMatch() {
        assertEquals(0, hsaService.freeTextSearch("NOT FOUND", -1).size());
        assertEquals(0, hsaService.freeTextSearch("",          -1).size());
        assertEquals(0, hsaService.freeTextSearch(null,        -1).size());
    }
	
	/**
	 * 1 node match the HSA ID "SE0000000002-1234"
	 */
	@Test
	public void testSearchOneHsaId() {

		String hsaId = "SE0000000002-1234";
		List<HsaNode> list = hsaService.freeTextSearch(hsaId, -1);

		assertEquals(1, list.size());
		assertEquals(hsaId, list.get(0).getHsaId());
	}

	/**
	 * 1 node match "Nässjö VC DLM" in its DN
	 */
	@Test
	public void testSearchOneDN() {

		String vc = "Nässjö VC DLM";
		List<HsaNode> list = hsaService.freeTextSearch(vc, -1);

		assertEquals(1, list.size());
		assertTrue(StringUtils.containsIgnoreCase(list.get(0).getDn().toString(), vc));
	}

	/**
	 * 6 nodes match the HSA ID "SE 1234"
	 */
	@Test
	public void testSearchMultipleHsaId() {

		List<HsaNode> list = hsaService.freeTextSearch("SE 1234", -1);

		assertEquals(6, list.size());
	}

	/**
	 * 2 nodes match "Nässjö VC" in their DN
	 */
	@Test
	public void testSearchMultipleDN() {

		List<HsaNode> list = hsaService.freeTextSearch("Nässjö VC", -1);

		assertEquals(2, list.size());
	}

	/**
	 * 6 nodes match the HSA ID "SE 1234", but we limit the result to max 2 hits
	 */
	@Test
	public void testSearchMultipleWithMaxNoOfHits() {

		List<HsaNode> list = hsaService.freeTextSearch("SE 1234", 2);

		assertEquals(2, list.size());
	}

	/**
	 * 6 nodes match the HSA ID "SE 1234", but only 5 match "Höglandets sjukvårdsområde" in their DN
	 *
	 */
	@Test
	public void testSearchBothHsaIdAndDN() {

		List<HsaNode> list = hsaService.freeTextSearch("SE 1234 Höglandets sjukvårdsområde", -1);

		assertEquals(5, list.size());
	}

}
