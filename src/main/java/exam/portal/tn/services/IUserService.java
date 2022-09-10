package exam.portal.tn.services;

import java.util.Set;

import exam.portal.tn.entities.User;
import exam.portal.tn.entities.UserRole;

public interface IUserService {
	
	
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
    public User getUser(String username);
    public void deleteUser(Long userId);
}
