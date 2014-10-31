package se.skltp.av

class LogiskAdress {
	
	String hsaId
	
	static belongsTo = [ProducentAnslutning, ProducentBestallning]
	
	static hasMany = [producentAnslutning: ProducentAnslutning, producentBestallning: ProducentBestallning]

    static constraints = {
		hsaId(unique: true, nullable: false, blank: false)
    }
	
	String toString(){
		return hsaId
	}
}
