import se.skltp.av.User
import se.skltp.av.Role
import org.apache.shiro.crypto.hash.Sha256Hash

class BootStrap {

   def init = { servletContext ->

        def adminRole = new Role(name: 'ADMINISTRATÖR')
        adminRole.addToPermissions("User:*")
        adminRole.addToPermissions("Role:*")
        adminRole.addToPermissions("Tjanstekomponent:index")
        adminRole.addToPermissions("Tjanstekomponent:list")
        adminRole.addToPermissions("Tjanstekomponent:show")
        adminRole.save()

        def tkResponsibleUserRole = new Role(name:"TJÄNSTEKOMPONENTANSVARIG")
        tkResponsibleUserRole.addToPermissions("Tjanstekomponent:*")
        tkResponsibleUserRole.save()

        def adminUser = new User(
          username: "admin",
          email: "admin@lorumipsum.nu",
          passwordHash: new Sha256Hash("password").toHex())
        adminUser.addToRoles(adminRole)
        adminUser.save()

        def tkResponsible = new User(
          username: "kallekula",
          email: "kallekula@lorumipsum.nu" ,
          passwordHash: new Sha256Hash("password").toHex())
        tkResponsible.addToRoles(tkResponsibleUserRole)
        tkResponsible.save()
    }

    def destroy = {
    }
}
