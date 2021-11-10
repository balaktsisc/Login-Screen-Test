import java.util.Locale;
import java.util.Scanner;
import java.util.HashSet;
import java.io.Console;

/** This class represents a Login Screen on system console. The user chooses between signing up for a new or signing in
 * an existing account, filling the personal detail list or using their username and password, respectively in each case.
 */

public class LoginScreen {
	private static HashSet<User> users;

	private static void initialize(){
		users = new HashSet<>();
		User user0 = new User("admin0", "000000");
		User user1 = new User("owner0", "111111");
		User user2 = new User("customer0", "222222");
		user0.setRole(Role.ADMIN);
		user1.setRole(Role.OWNER);
		user2.setRole(Role.CUSTOMER);
		user0.setValid(true);
		user1.setValid(true);
		user2.setValid(true);
		users.add(user0);
		users.add(user1);
		users.add(user2);
	}

	private static User checkUser(String name, String pass) {
		User tempUser0 = new User(name, pass);
		for(User user : users)
			if(tempUser0.equals(user))
				return user;
		return null;
	}

	public static void showConsole() {
		Scanner input = new Scanner(System.in);
		String username, password, reqRole;
		Role role;
		User loggedUser;
		Console console = System.console();
		if(console == null) {
			System.out.print("No console available");
			return;
		}
		char[] pass;
		char choice;
		initialize();

		System.out.println("Welcome to MyBooking!\nPress [1] to create a new account or [2] to sign in.");
		choice = input.nextLine().charAt(0);
		switch(choice) {
			case '1':
				System.out.println("Please, provide the following information and your account will be activated soon!");
				System.out.print("Username: ");
				username = input.nextLine();
				System.out.print("Password: ");
				pass = console.readPassword();
				password = new String(pass);
				loggedUser = new User(username, password);
				System.out.print("Address: ");
				loggedUser.setAddress(input.nextLine());
				System.out.print("Telephone: +");
				loggedUser.setTelephone(input.nextInt());
				System.out.print("Email: ");
				loggedUser.setEmail(input.nextLine());
				System.out.print("Role (Admin | Customer | Owner): ");
				reqRole = input.nextLine().toUpperCase(Locale.ROOT);
				role = switch (reqRole) {
					case "ADMIN" -> Role.ADMIN;
					case "OWNER" -> Role.OWNER;
					case "CUSTOMER" -> Role.CUSTOMER;
					default -> Role.USER;
				};
				loggedUser.setRole(role);
				break;
			case '2':
				System.out.println("Please, log-in with your account details.");
				int checks = 0;
				do {
					if (checks != 0)
						System.out.println("Wrong Details. " + (4-checks) + " times remaining. Try again.\n");
					System.out.print("Username: ");
					username = input.nextLine();
					System.out.print("Password: ");
					pass = console.readPassword();
					password = new String(pass);
					checks++;
				} while ((loggedUser = checkUser(username, password)) == null && checks <= 3);
				if (checks > 3) {
					System.out.println("Contact administrator!");
					return;
				}
				break;
			default:
				return;
		}
		if(loggedUser.isValid()) {
			System.out.println("Welcome " + loggedUser.getUsername() +"!");
		}
		else {
			System.out.println("Please, wait until an Administrator activates your account!");
		}
	}
}