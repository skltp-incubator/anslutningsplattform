package se.skltp.av

class ServiceProducerConnection {
	
	String rivTaProfile
	String url
	String serviceContract
	Date validFromTime
	Date validToTime
	
	static belongsTo = [serviceProducerOrder: ServiceProducerOrder]
	
	static hasMany = [logicalAddresses: LogicalAddress]

    static constraints = {
		url(nullable: false, blank: false)
		serviceContract(nullable: false, blank: false)
    }
	
	String toString(){
		return "$serviceContract, $url"
	}
}
