package se.skltp.av

class Tjansteproducent {

    String hsaId
    String kontaktNamn
    String kontaktEpost
    String kontaktTelefon

    static belongsTo = [user: Anvandare]
	
    static constraints = {
      hsaId blank: false, unique: true
      kontaktEpost email: true, blank: false
    }

    String toString(){
      return "HSA ID: $hsaId, Kontakt: $kontaktEpost"
    }
}
