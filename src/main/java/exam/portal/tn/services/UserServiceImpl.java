package exam.portal.tn.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import exam.portal.tn.entities.User;
import exam.portal.tn.entities.UserRole;
import exam.portal.tn.repository.RoleRepository;
import exam.portal.tn.repository.UserRepository;
@Service
public class UserServiceImpl implements IUserService {
	
	
	
	@Autowired 
	UserRepository userRepo;
	@Autowired 
    RoleRepository roleRepo;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local=userRepo.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("User is already there!!");
			throw new Exception("User already present");
		}else {
			//usercreate
			for(UserRole ur:userRoles) {
				roleRepo.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local=userRepo.save(user);
		}
		
		return local;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepo.deleteById(userId);
		
	}

}
