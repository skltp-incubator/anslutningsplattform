package se.skltp.av

class LogiskAdress {
	
	String hsaId

    static constraints = {
		hsaId(nullable: false, blank: false)
    }
}
