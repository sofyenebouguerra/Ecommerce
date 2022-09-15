package exam.portal.tn.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.portal.tn.entities.Role;
import exam.portal.tn.entities.User;
import exam.portal.tn.entities.UserRole;
import exam.portal.tn.services.IUserService;
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {
@Autowired
IUserService userService;

      @PostMapping("/")
      public User createUser(@RequestBody User user) throws Exception{
    	  user.setProfile("default.png");
		Set<UserRole> roles=new HashSet<>() ;
		Role role=new Role();
		role.setRoleId(77L);
		role.setRoleName("Capitain");
		UserRole userRole=new UserRole();
		//userRole.setUser(user);
		//userRole.setRole(role);
		//userRole.setUserRoleId(15L);
		userRole.setRole(role);
		userRole.setUser(user);
		roles.add(userRole);
		return userService.createUser(user, roles);
    	  
      }
      @GetMapping("/{username}")
      public User getUser(@PathVariable("username")  String username) {
    	  return userService.getUser(username);
      }
      @DeleteMapping("/Del/{id}")
      public void deleteUser(@PathVariable ("id") Long userId) {
    	   userService.deleteUser(userId);
      }
}
