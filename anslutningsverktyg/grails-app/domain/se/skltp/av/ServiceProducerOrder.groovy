package se.skltp.av

class ServiceProducerOrder {
	
	String environment
	
	String defaultRivTaProfile
	
	ServiceProducer serviceProducer
		
	static hasMany = [orderHistory: OrderHistory, serviceProducerConnection: ServiceProducerConnection, defaultLogicalAddresses: LogicalAddress]
	
    static constraints = {
		environment(nullable: false, blank: false)
    }
	
	String toString(){
		return "$environment, $serviceProducer"
	}
}
