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
        adminRole.save()

       
		//Users
        def adminUser = new User(
          username: "admin",
          epost: "admin@lorumipsum.nu",
          passwordHash: new Sha256Hash("password").toHex())
        adminUser.addToRoles(adminRole)
        adminUser.save()
   }


    def destroy = {
    }
}
