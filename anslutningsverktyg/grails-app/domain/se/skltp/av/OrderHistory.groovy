package se.skltp.av

class OrderHistory {
	
	String status
	Date date
	String lastUpdatedBy
	
	static belongsTo = [serviceProducerOrder: ServiceProducerOrder]

    static constraints = {
    }
	
	def beforeUpdate() {
		//Hook to set lastUpdatedBy
	 }
}
