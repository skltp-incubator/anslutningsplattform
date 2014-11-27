package se.skltp.av.services.dto

import grails.validation.Validateable;
import groovy.transform.ToString;

@Validateable
@ToString
class AnsvarigDTO {
	long id
	String name
	String email
	String phone
}
