package se.skltp.av

class ProducentBestallning {
	
	String miljo
	
	String status
	
	static belongsTo = [tjanstekomponent: Tjanstekomponent]
		
	static hasMany = [bestallningsHistorik: BestallningsHistorik, producentAnslutning: ProducentAnslutning]
	
    static constraints = {
		miljo(nullable: false, blank: false)
    }
}
