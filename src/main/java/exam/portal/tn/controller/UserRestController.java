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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import exam.portal.tn.entities.Role;
import exam.portal.tn.entities.User;
import exam.portal.tn.entities.UserRole;
import exam.portal.tn.helper.UserFoundException;
import exam.portal.tn.services.IUserService;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParseException;
import java.io.File;
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {
	
	@Autowired
	IUserService userService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	 @Autowired  ServletContext context;

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
      
      
      @PostMapping("/users")
 	 public User createUser (@RequestParam("file") MultipartFile file,
 			 @RequestParam("user") String user) throws JsonParseException , JsonMappingException , Exception
 	 {
 		 System.out.println("Save Article.............");
 	    User userr = new ObjectMapper().readValue(user, User.class);
 	    boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
 	    if (!isExit)
 	    {
 	    	new File (context.getRealPath("/Imagess/")).mkdir();
 	    	System.out.println("mk dir Imagess.............");
 	    }
 	    System.out.println("Save User  22222.............");
 	    String filename = file.getOriginalFilename();
 	    String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
 	    File serverFile = new File (context.getRealPath("/Imagess/"+File.separator+newFileName));
 	    try
 	    {
 	    	System.out.println("Image");
 	    	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
 	    	 
 	    }catch(Exception e) {
 	    	e.printStackTrace();
 	    }
 	    System.out.println("Save User 333333.............");
 	  
 	   userr.setProfile(newFileName);
 	  userr.setPassword(this.bCryptPasswordEncoder.encode(userr.getPassword()));
	  
		Set<UserRole> roles=new HashSet<>() ;
		Role role=new Role();
		role.setRoleId(98L);
		role.setRoleName("ADMIN");
		UserRole userRole=new UserRole();
		//userRole.setUser(user);
		//userRole.setRole(role);
		//userRole.setUserRoleId(15L);
		userRole.setRole(role);
		userRole.setUser(userr);
		roles.add(userRole);
		return userService.createUser(userr, roles);
 	 }
      @GetMapping(path="/Imgusers/{id}")
		 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
			 User User   =userService.findUserById(id);
			 return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/")+User.getProfile()));
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

