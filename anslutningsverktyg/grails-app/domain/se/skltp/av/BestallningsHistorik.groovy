package se.skltp.av

class BestallningsHistorik {
	
	String status
	Date datum
	String senastUppdateradAv
	
	static belongsTo = [producentBestallning: ProducentBestallning]

    static constraints = {
    }
	
	def beforeUpdate() {
		//Hook to set lastUpdatedBy
	 }
}
