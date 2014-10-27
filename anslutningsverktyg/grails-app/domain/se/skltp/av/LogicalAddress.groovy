package se.skltp.av

class LogicalAddress {
	
	String hsaId

    static constraints = {
		hsaId(nullable: false, blank: false)
    }
}
