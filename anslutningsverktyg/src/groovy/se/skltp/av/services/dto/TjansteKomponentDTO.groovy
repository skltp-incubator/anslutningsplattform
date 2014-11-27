package se.skltp.av.services.dto

import grails.validation.Validateable
import groovy.transform.ToString
import se.skltp.av.ProducentBestallning
import se.skltp.av.User

@Validateable
@ToString
class TjansteKomponentDTO {
    long id
    String hsaId
    String namn
    String huvudAnsvarigNamn
    String huvudAnsvarigEpost
    String huvudAnsvarigTelefon
    String tekniskKontaktEpost
    String tekniskKontaktNamn
    String tekniskKontaktTelefon
    String funktionsBrevladaEpost
    String funktionsBrevladaTelefon
    String ipadress
	String pingForConfiguration
}
