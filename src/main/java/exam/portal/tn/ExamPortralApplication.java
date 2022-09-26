package exam.portal.tn;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import exam.portal.tn.entities.Role;
import exam.portal.tn.entities.User;
import exam.portal.tn.entities.UserRole;
import exam.portal.tn.services.IUserService;

@SpringBootApplication 
public class ExamPortralApplication 
{
	


	public static void main(String[] args) {
		SpringApplication.run(ExamPortralApplication.class, args);
		
	}

	/*@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("applicatio is runnoing");
		User user2=new User();
		user2.setFirstName("dddd");
		user2.setEmail("bvvv");
		user2.setEnabled(false);
		user2.setLastName("nhff");
		user2.setPassword("xxxxxxx");
		user2.setUsername("xxxxxxxccc");
		user2.setPhone("jkk");
		
		Role role2=new Role();
		role2.setRoleId(99L);
		role2.setRoleName("Abdesslem");
		
		Set<UserRole> userRoles=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role2);
		userRole.setUser(user2);
		userRoles.add(userRole);
		User user1=userserv.createUser(user2, userRoles);
		System.out.println(user1.getUsername());*/
		
		
		
		
				
		
	

}
