package se.skltp.av

import java.util.Date;

class ProducentAnslutning {
	
	private static final Date NULL_DATE = new Date(0)
	
	String rivTaProfile
	String url
	String tjansteKontrakt
	Date validFromTime
	Date validToTime
	
	//To cascade deletes when ProducentBestallning is removed
	static belongsTo = [producentBestallning: ProducentBestallning]
	
	static hasMany = [logiskAdresser: LogiskAdress]

    static constraints = {
		url(nullable: false, blank: false)
		tjansteKontrakt(nullable: false, blank: false)
    }
	
	def beforeInsert() {
		if (validFromTime == NULL_DATE) {
			validFromTime = new Date()
		}
	}
}
