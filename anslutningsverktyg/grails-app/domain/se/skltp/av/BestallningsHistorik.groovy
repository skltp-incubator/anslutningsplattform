package se.skltp.av

class BestallningsHistorik {
	
	String status
	Date datum
	String senastUppdateradAv
	
	//To cascade deletes when ProducentBestallning is removed
	static belongsTo = [producentBestallning: ProducentBestallning, konsumentBestallning: KonsumentBestallning]

    static constraints = {
    }
	
	def beforeUpdate() {
		//Hook to set lastUpdatedBy
	 }
}
