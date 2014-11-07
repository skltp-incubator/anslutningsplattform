package se.skltp.av

class Tjanstekomponent {

    String hsaId
	String tekniskKontaktEpost
	String tekniskKontaktNamn
	String tekniskKontaktTelefon
	String funktionsBrevladaEpost
	String funktionsBrevladaTelefon
    String ipadress

    static belongsTo = [user: User]
	
	static hasMany = [producentBestallningar: ProducentBestallning]
	
    static constraints = {
      hsaId blank: false, unique: true
      tekniskKontaktEpost email: true, blank: false
	  funktionsBrevladaEpost email: true, blank: false
	  ipadress blank: false
    }
}
