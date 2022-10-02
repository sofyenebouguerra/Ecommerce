package exam.portal.tn.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import exam.portal.tn.entities.Role;
import exam.portal.tn.entities.User;
import exam.portal.tn.entities.UserRole;
import exam.portal.tn.helper.UserFoundException;
import exam.portal.tn.services.IUserService;
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {
	
	@Autowired
	IUserService userService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

      @PostMapping("/")
      public User createUser(@RequestBody User user) throws Exception{
    	  user.setProfile("default.png");
    	  
    	  
    	  //encoding passwor with BCryptPasswordEncoder
    	  
    	  user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
    	  
		Set<UserRole> roles=new HashSet<>() ;
		Role role=new Role();
		role.setRoleId(99L);
		role.setRoleName("ADMIN");
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
      
      //update api
    /*  @ExceptionHandler(UserFoundException.class)
      public ResponseEntity<?> exceptionHandler(UserFoundException ex){
		return ResponseEntity;
    	  
      }*/
      
      @PostMapping("/addUser")
  	User addUser(@RequestBody User user) {
  		return userService.addUser(user);
  	}

  	@GetMapping("/admin/findAllUsers")
  	public List<User> findAllUsers() {
  		return userService.findAllUsers();
  	}

  	@PutMapping("/editUser/{id}")
  	User editUser(@RequestBody User user, @PathVariable long id) {
  		return userService.editUser(user, id);
  	}

  	@GetMapping("/findUserById/{id}")
  	User findUserById(@PathVariable long id) {
  		return userService.findUserById(id);
  	}

  	@DeleteMapping("/deleteUser/{id}")
  	void deleteUser(@PathVariable long id) {
  		userService.deleteUser(id);
  	}
  	
  	@GetMapping("/findByUsername/{username}")
  	User findByUsername(@PathVariable String username) {
  		return userService.getUser(username);
  	}
  }

