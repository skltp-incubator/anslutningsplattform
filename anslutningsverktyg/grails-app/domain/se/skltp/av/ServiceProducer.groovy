package se.skltp.av

class ServiceProducer {

    String hsaId
    String contactName
    String contactEmail
    String contactPhone

    static belongsTo = [user: User]
	
    static constraints = {
      hsaId blank: false, unique: true
      contactEmail email: true, blank: false
    }

    String toString(){
      return "HSA ID: $hsaId, Kontakt: $contactEmail"
    }
}
