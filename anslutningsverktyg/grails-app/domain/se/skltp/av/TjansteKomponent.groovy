package se.skltp.av

class TjansteKomponent {

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
      tekniskKontaktEpost email: true, nullable: false, blank: false
	  tekniskKontaktNamn nullable: false, blank: false
	  tekniskKontaktTelefon nullable: true, blank: false
	  funktionsBrevladaEpost blank: false
	  funktionsBrevladaTelefon nullable: false, blank: false
	  ipadress nullable: true, blank: false
    }
}
