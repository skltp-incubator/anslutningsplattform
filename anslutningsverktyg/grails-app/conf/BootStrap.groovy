import se.skltp.av.Roll;
import se.skltp.av.Tjansteproducent;
import se.skltp.av.Anvandare;

import org.apache.shiro.crypto.hash.Sha256Hash

class BootStrap {

   def init = { servletContext ->
	   
	   	//Roles
        def adminRole = new Roll(name: 'ADMINISTRATÖR')
        adminRole.addToPermissions("User:*")
        adminRole.addToPermissions("Role:*")
        adminRole.addToPermissions("ServiceProducer:index")
        adminRole.addToPermissions("ServiceProducer:list")
        adminRole.addToPermissions("ServiceProducer:show")
		adminRole.addToPermissions("Environment:*")
        adminRole.save()

        def tkResponsibleUserRole = new Roll(name:"TJÄNSTEKOMPONENTANSVARIG")
        tkResponsibleUserRole.addToPermissions("ServiceProducer:*")
		tkResponsibleUserRole.addToPermissions("ServiceProducerOrder:*")
		tkResponsibleUserRole.addToPermissions("ServiceProducerConnection:*")
		tkResponsibleUserRole.addToPermissions("OrderHistory:*")
		tkResponsibleUserRole.addToPermissions("Environment:*")
        tkResponsibleUserRole.save()

		//Users
        def adminUser = new Anvandare(
          username: "admin",
          epost: "admin@lorumipsum.nu",
          passwordHash: new Sha256Hash("password").toHex())
        adminUser.addToRoles(adminRole)
        adminUser.save()

        def tkResponsible = new Anvandare(
          username: "kallekula",
          epost: "kallekula@lorumipsum.nu" ,
          passwordHash: new Sha256Hash("password").toHex())
        tkResponsible.addToRoles(tkResponsibleUserRole)
        tkResponsible.save()
		
		//Service producers
		def serviceProducer1 = new Tjansteproducent(
			hsaId: "HSASERVICES123Q",
			kontaktNamn: "Agda Andersson",
			kontaktEpost: "ansvarig@tjansteproducent.nu",
			kontaktTelefon: "1234567890",
			user: tkResponsible
			).save()
		
		def serviceProducer2 = new Tjansteproducent(
			hsaId: "HSASERVICES456Z",
			kontaktNamn: "Tolvan Tolvansson",
			kontaktEpost: "ansvarig@tjansteproducent.nu",
			kontaktTelefon: "0987654321",
			user: tkResponsible
			).save()
    }

    def destroy = {
    }
}
