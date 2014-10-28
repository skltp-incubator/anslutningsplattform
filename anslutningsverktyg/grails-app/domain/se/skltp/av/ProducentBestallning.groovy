package se.skltp.av

class ProducentBestallning {
	
	String miljo
	
	String defaultRivTaProfile
	
	static belongsTo = [tjansteproducent: Tjansteproducent]
		
	static hasMany = [bestallningsHistorik: BestallningsHistorik, producentAnslutning: ProducentAnslutning, defaultLogiskAdress: LogiskAdress]
	
    static constraints = {
		miljo(nullable: false, blank: false)
    }
	
	String toString(){
		return "$miljo, $tjansteproducent"
	}
}
