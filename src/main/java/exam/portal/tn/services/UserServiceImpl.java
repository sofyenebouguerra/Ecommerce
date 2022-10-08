package exam.portal.tn.services;

import java.util.List;
import java.util.Optional;
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
 //////////////////////////////////////////
	@Override
	public User addUser(User user) {
		List<User> users = userRepo.findAll();
		if (users.size() == 0) {
			user.setAdmin(true);
		}
		
		for (User userExist : users) {
			if (user.getUsername().equals(userExist.getUsername())) {
				userExist.setUsername(userExist.getUsername());
				userExist.setPassword(userExist.getPassword());
				return userRepo.save(userExist);
			}
		}
	
		return userRepo.save(user);	
	}

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User editUser(User user, long id) {
		User existUser = userRepo.findById(id).orElse(null);
		existUser.setUsername(user.getUsername());
		existUser.setPassword(user.getPassword());
		existUser.setAdmin(user.isAdmin());
		existUser.setEmail(user.getEmail());
		existUser.setNameOnCard(user.getNameOnCard());
		existUser.setCardNumber(user.getCardNumber());
		existUser.setCvv(user.getCvv());
		existUser.setAddress(user.getAddress());
		existUser.setProfile(user.getProfile());
		return userRepo.save(existUser);
	}

	@Override
	public User findUserById(long id) {
      return userRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteUser(long id) {
		userRepo.deleteById(id);
	} 
	
	




}
