package exam.portal.tn.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long userRoleId;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;

	@ManyToOne(fetch=FetchType.EAGER)
	private Role role;
	
	
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
    
    
}
