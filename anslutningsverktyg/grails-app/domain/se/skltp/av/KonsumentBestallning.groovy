package se.skltp.av

class KonsumentBestallning {
	
	String miljo

	String status
	
	static belongsTo = [tjanstekomponent: Tjanstekomponent]
		
	static hasMany = [bestallningsHistorik: BestallningsHistorik, konsumentAnslutning: KonsumentAnslutning]
	
    static constraints = {
		miljo(nullable: false, blank: false)
    }
}
