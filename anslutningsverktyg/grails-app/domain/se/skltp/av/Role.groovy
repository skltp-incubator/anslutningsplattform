package se.skltp.av

/**
 * Currently named Role instead of Roll to map with ShiroDbRealm
 * 
 */
class Role {
    String name

    static hasMany = [ anvandare: User, permissions: String ]
    static belongsTo = User

    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }

    String toString(){
      return name
    }
}
