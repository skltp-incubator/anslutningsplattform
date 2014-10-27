package se.skltp.av

class Roll {
    String name

    static hasMany = [ anvandare: Anvandare, permissions: String ]
    static belongsTo = Anvandare

    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }

    String toString(){
      return name
    }
}
