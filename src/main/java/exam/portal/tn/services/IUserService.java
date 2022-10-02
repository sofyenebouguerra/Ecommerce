package exam.portal.tn.services;

import java.util.List;
import java.util.Set;

import exam.portal.tn.entities.User;
import exam.portal.tn.entities.UserRole;

public interface IUserService {
	
	
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
    public User getUser(String username);
    public void deleteUser(Long userId);
	
    User addUser(User user);
    
    List<User> findAllUsers();
    
    User editUser(User user, long id);
    
    User findUserById(long id);
    
    void deleteUser(long id);
    
 
}
