package se.skltp.av

class Anvandare {
    String username
    String passwordHash
    String epost
    Date datumSkapad
    Date datumUppdaterad

    static hasMany = [ roles: Roll, permissions: String, tjanstekomponenter: Tjansteproducent ]

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
