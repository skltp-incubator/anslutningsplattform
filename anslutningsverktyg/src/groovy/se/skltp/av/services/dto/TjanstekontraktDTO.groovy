package se.skltp.av.services.dto

import grails.validation.Validateable;
import groovy.transform.ToString;

@Validateable
@ToString
class TjansteKontraktDTO {
    String namn
    String namnrymd
    int majorVersion
    int minorVersion
    boolean installedInEnvironment
}
