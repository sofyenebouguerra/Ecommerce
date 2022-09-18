package exam.portal.tn.helper;

public class UserFoundException extends Exception{
	public UserFoundException() {
		super("User with this Username is already is DB !! try with another ");	}
     public UserFoundException(String msg) {
	   super(msg);
}

}
