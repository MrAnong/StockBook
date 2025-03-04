package StockBook.dto.utilities;

public class UsersDetails {

	private String email_PhoneNumber_Username;
    private String password;

    public UsersDetails() {
    }

    public String getEmail_PhoneNumber_Username() {
        return email_PhoneNumber_Username;
    }

    public void setEmail_PhoneNumber_Username(String email_PhoneNumber) {
        this.email_PhoneNumber_Username = email_PhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
