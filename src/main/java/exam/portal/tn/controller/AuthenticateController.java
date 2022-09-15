package exam.portal.tn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.portal.tn.config.JwtUtils;
import exam.portal.tn.entities.JwtRequest;
import exam.portal.tn.entities.JwtResponse;
import exam.portal.tn.helper.UserNotFoundException;
import exam.portal.tn.services.UserDetailsIServiceImpl;

@RestController
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsIServiceImpl detailsImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//generate token
	
	/*@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Throwable{
		try
		{
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			
		}catch(UserNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
		
		UserDetails userDetails=this.detailsImpl.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token) );
	}*/
	
	
	
      @PostMapping("/generate-token")
	   public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try
		{
			//authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}catch(UserNotFoundException e) {
			
			e.printStackTrace();
			throw new Exception("User not found");
		}
		/////////authenticate
		UserDetails userDetails=this.detailsImpl.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token) );
		
	}
	
	
	private void authenticate(String username,String password) throws Exception
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(DisabledException e)
		{
			throw new Exception("User Disabled " +e.getMessage());
		}catch(BadCredentialsException e)
		{
			throw new Exception("Invalid Credentials "+e.getMessage());
		}
	}

}
