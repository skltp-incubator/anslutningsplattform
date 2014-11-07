package se.skltp.av

/**
 * Currently named User instead of Anvandare to map with ShiroDbRealm
 *
 */
class User {
	
	private static final Date NULL_DATE = new Date(0)
	
	String namn
	String username
	String passwordHash
	String epost
	String telefonNummer
	Date datumSkapad
	Date datumUppdaterad

	static hasMany = [ roles: Role, permissions: String, tjanstekomponenter: Tjanstekomponent ]

	static constraints = {
		namn(nullable: false, blank: false)
		username(nullable: false, blank: false, unique: true)
		passwordHash blank: false
		datumSkapad nullable: false
		datumUppdaterad nullable: true
		epost email: true, blank: false
	}

	def beforeInsert() {
		if (datumSkapad == NULL_DATE) {
			datumSkapad = new Date()
		}
	}
	
	def beforeUpdated() {
		if (datumUppdaterad == NULL_DATE) {
			datumUppdaterad = new Date()
		}
	}
}
