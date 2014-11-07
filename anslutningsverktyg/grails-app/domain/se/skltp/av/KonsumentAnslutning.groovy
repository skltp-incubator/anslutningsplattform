package se.skltp.av

import java.util.Date;

class KonsumentAnslutning {
	
	private static final Date NULL_DATE = new Date(0)
	
	String tjansteKontrakt
	Date validFromTime
	Date validToTime
	
	static belongsTo = [konsumentBestallning: KonsumentBestallning]
	
	static hasMany = [logiskAdresser: LogiskAdress]

    static constraints = {
		tjansteKontrakt(nullable: false, blank: false)
    }
	
	def beforeInsert() {
		if (validFromTime == NULL_DATE) {
			validFromTime = new Date()
		}
	}
	
}
