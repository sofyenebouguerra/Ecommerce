package exam.portal.tn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import exam.portal.tn.entities.User;

import exam.portal.tn.repository.UserRepository;

@Service
public class UserDetailsIServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.findByUsername(username);
		if(user==null) {
			System.out.println("User not found!!");
			throw new UsernameNotFoundException("No user found !!");
		}
		return user;
	}
	
	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRep.findByUsername(username);
		if(user==null) {
			System.out.println("User not found!!");
			throw new UsernameNotFoundException("No user found !!");
		}
		return  user;
	}*/

}
