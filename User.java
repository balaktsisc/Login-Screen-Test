import java.awt.*;  

public class User {
	private final String username;
	private String password, address, email;
	private Role role;
	private Image photo;
	private boolean valid;
	private int telephone;

	public User(Role role, String name, String password, Image photo) {
		this.role = role;
		this.username = name;
		this.password = password;
		this.photo = photo;
		this.valid = false;
		this.telephone = 0;
		this.address = null;
		this.email = null;
	}

	public User(String name, String password) {
		this.role = Role.USER;
		this.username = name;
		this.password = password;
		this.photo = null;
		this.valid = false;
		this.telephone = 0;
		this.address = null;
		this.email = null;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return this.role;
	}

	public String getAddress() {
		return this.address;
	}

	public String getEmail() {
		return this.email;
	}

	public int getTelephone() {
		return this.telephone;
	}

	public Image getPhoto() {
		return photo;
	}

	public boolean isValid() {
		return valid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setValid(Boolean validation) {
		this.valid = validation;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}

	public boolean approveUser(User user) {			//Only an Administrator can validate a new admin.
		if(this.role == Role.ADMIN)
			return (user.valid = true);
		else 
			return false;
	}

	@Override
	public boolean equals(Object v) {
		boolean retVal = false;

		if (v instanceof User){
			User ptr = (User) v;
			retVal = ptr.getUsername().equals(this.getUsername()) && ptr.getPassword().equals(this.getPassword());
		}

		return retVal;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + (this.getUsername() != null ? this.getUsername().hashCode() : 0);
		return hash;
	}


}