package exam.portal.tn.helper;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
			super("User with this Username not found in database !! ");	}
	public UserNotFoundException(String msg) {
		super(msg);
	}

}
