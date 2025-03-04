package StockBook.dto.responses;

import StockBook.model.Users;

public class UsersResponse {

	private String message;
    private Users user;
    private String token;

    public UsersResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
