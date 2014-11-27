package se.skltp.av.services.dto

import grails.validation.Validateable;
import groovy.transform.ToString;

@Validateable
@ToString
class TakRoutingEntryDTO {
    String id
    String name
	String url
	
	String toString() {
		StringBuilder sb = new StringBuilder()
		sb.append(getClass().getSimpleName())
		sb.append(" [id: ")
		sb.append(id)
		sb.append(", name: ")
		sb.append(name)
		sb.append(", url: ")
		sb.append(url)
		sb.append("]")
	}
}
