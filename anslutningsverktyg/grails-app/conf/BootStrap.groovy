import se.skltp.av.User
import se.skltp.av.Role
import se.skltp.av.ServiceProducer

import org.apache.shiro.crypto.hash.Sha256Hash

class BootStrap {

   def init = { servletContext ->
	   
	   	//Roles
        def adminRole = new Role(name: 'ADMINISTRATÖR')
        adminRole.addToPermissions("User:*")
        adminRole.addToPermissions("Role:*")
        adminRole.addToPermissions("ServiceProducer:index")
        adminRole.addToPermissions("ServiceProducer:list")
        adminRole.addToPermissions("ServiceProducer:show")
		adminRole.addToPermissions("Environment:*")
        adminRole.save()

        def tkResponsibleUserRole = new Role(name:"TJÄNSTEKOMPONENTANSVARIG")
        tkResponsibleUserRole.addToPermissions("ServiceProducer:*")
		tkResponsibleUserRole.addToPermissions("ServiceProducerOrder:*")
		tkResponsibleUserRole.addToPermissions("ServiceProducerConnection:*")
		tkResponsibleUserRole.addToPermissions("OrderHistory:*")
		tkResponsibleUserRole.addToPermissions("Environment:*")
        tkResponsibleUserRole.save()

		//Users
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
		
		//Service producers
		def serviceProducer1 = new ServiceProducer(
			hsaId: "HSASERVICES123Q",
			contactName: "Agda Andersson",
			contactEmail: "ansvarig@tjansteproducent.nu",
			contactPhone: "1234567890",
			user: tkResponsible
			).save()
		
		def serviceProducer2 = new ServiceProducer(
			hsaId: "HSASERVICES456Z",
			contactName: "Tolvan Tolvansson",
			contactEmail: "ansvarig@tjansteproducent.nu",
			contactPhone: "0987654321",
			user: tkResponsible
			).save()
    }

    def destroy = {
    }
}
