import se.skltp.av.Role;
import se.skltp.av.Tjansteproducent;
import se.skltp.av.User;

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
        adminRole.save()

        def tkResponsibleUserRole = new Role(name:"TJÄNSTEKOMPONENTANSVARIG")
        tkResponsibleUserRole.addToPermissions("TjansteProducent:*")
		tkResponsibleUserRole.addToPermissions("ProducentBestallning:*")
		tkResponsibleUserRole.addToPermissions("ProducentAnslutning:*")
		tkResponsibleUserRole.addToPermissions("BestallningsHistorik:*")
		tkResponsibleUserRole.addToPermissions("BekraftaProducentBestallning:*")
		tkResponsibleUserRole.addToPermissions("RegistreraLogiskAdress:*")
		tkResponsibleUserRole.addToPermissions("RegistreraTjansteKontrakt:*")
        tkResponsibleUserRole.save()

		//Users
        def adminUser = new User(
          username: "admin",
          epost: "admin@lorumipsum.nu",
          passwordHash: new Sha256Hash("password").toHex())
        adminUser.addToRoles(adminRole)
        adminUser.save()

        def tkResponsible = new User(
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
