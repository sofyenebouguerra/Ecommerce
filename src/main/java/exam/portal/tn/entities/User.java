package exam.portal.tn.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="users")
public class User  implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long id;
	
	private String username;
	private String password;
	private String firstName; 
	private String lastName;
	private String email;
	private String phone; 
	private boolean enabled=true;
	private String profile;
    private boolean admin;
	 private String nameOnCard;
	 private String cardNumber; 
	 private int cvv;
	 private String address;
	 
	 @JsonProperty(access = Access.AUTO)
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	 private List<Category> categories;
	 
	 @JsonProperty(access = Access.AUTO)
	 @OneToMany(cascade = CascadeType.MERGE, mappedBy = "user")
	 private List<Cart> carts;

	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="user")
	@JsonIgnore
	private Set<UserRole> userRoles=new HashSet<>();
	
	@ManyToMany
	@JsonIgnore
	private List<Comment> comments;

	
	public User() {
		
	}
	
	
	
	public User(Long id, String username, String password, String firstName, String lastName, String email,
			String phone, boolean enabled, String profile, boolean admin, String nameOnCard, String cardNumber, int cvv,
			String address, List<exam.portal.tn.entities.Category> categories, List<exam.portal.tn.entities.Cart> carts,
			Set<UserRole> userRoles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.profile = profile;
		this.admin = admin;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.address = address;
		this.categories = categories;
		this.carts = carts;
		this.userRoles = userRoles;
	}



	
	public User(String username, String password, String firstName, String lastName, String email, String phone,
			boolean enabled, String profile, boolean admin, String nameOnCard, String cardNumber, int cvv,
			String address, List<exam.portal.tn.entities.Category> categories, List<exam.portal.tn.entities.Cart> carts,
			Set<UserRole> userRoles) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.profile = profile;
		this.admin = admin;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.address = address;
		this.categories = categories;
		this.carts = carts;
		this.userRoles = userRoles;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> set=new HashSet<>();
		this.userRoles.forEach(userRole->{
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	public boolean isAdmin() {
		return admin;
	}



	public void setAdmin(boolean admin) {
		this.admin = admin;
	}



	public String getNameOnCard() {
		return nameOnCard;
	}



	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	public int getCvv() {
		return cvv;
	}



	public void setCvv(int cvv) {
		this.cvv = cvv;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public List<Category> getCategories() {
		return categories;
	}



	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}



	public List<Cart> getCarts() {
		return carts;
	}



	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}


	public void addCategoryToUser(Category category) {
		if (getCategories()==null) {
			this.categories = new ArrayList<>();
		}
		getCategories().add(category);
		category.setUser(this);
	}
   
	public void addCartToUser(Cart cart) {
		if(getCarts()==null) {
			this.carts = new ArrayList<>();	
		}
		getCarts().add(cart);
		cart.setUser(this);
	}
	public void removeFromCart(Cart cart) {
		if (getCarts()!=null) {
			getCarts().remove(cart);
		}
	}
	 
	

}
