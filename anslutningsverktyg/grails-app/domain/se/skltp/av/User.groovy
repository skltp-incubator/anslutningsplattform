package se.skltp.av

class User {
    String username
    String passwordHash
    String email
    Date dateCreated
    Date lastUpdated

    static hasMany = [ roles: Role, permissions: String, tjanstekomponenter: ServiceProducer ]

    static constraints = {
        username(nullable: false, blank: false, unique: true)
        passwordHash blank: false
        dateCreated nullable: true
        lastUpdated nullable: true
        email email: true, blank: false
    }

    String toString(){
      return username
    }
}
