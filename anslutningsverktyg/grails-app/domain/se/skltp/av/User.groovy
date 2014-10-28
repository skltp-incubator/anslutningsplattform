package se.skltp.av

/**
 * Currently named User instead of Anvandare to map with ShiroDbRealm
 *
 */
class User {
    String username
    String passwordHash
    String epost
    Date datumSkapad
    Date datumUppdaterad

    static hasMany = [ roles: Role, permissions: String, tjanstekomponenter: Tjansteproducent ]

    static constraints = {
        username(nullable: false, blank: false, unique: true)
        passwordHash blank: false
        datumSkapad nullable: true
        datumUppdaterad nullable: true
        epost email: true, blank: false
    }

    String toString(){
      return username
    }
}
