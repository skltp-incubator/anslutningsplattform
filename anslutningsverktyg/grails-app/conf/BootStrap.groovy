import se.skltp.av.Role;
import se.skltp.av.Tjanstekomponent;
import se.skltp.av.User;

import org.apache.shiro.crypto.hash.Sha256Hash

class BootStrap {

	def init = { servletContext ->

		//Roles
		def adminRole = new Role(name: 'ADMINISTRATÖR')
		adminRole.addToPermissions("*:*")
		adminRole.addToPermissions("*:*")
		adminRole.save()


		//Users
		def adminUser = new User(
				namn: "Agda Andersson",
				username: "admin",
				epost: "admin@lorumipsum.nu",
				datumSkapad: new Date(),
				passwordHash: new Sha256Hash("password").toHex())
		
		adminUser.addToRoles(adminRole)
		adminUser.save()
	}


	def destroy = {
	}
}
