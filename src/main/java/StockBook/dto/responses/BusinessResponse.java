package StockBook.dto.responses;

import StockBook.model.Business;

public class BusinessResponse {

	private String message;
    private Business business;

    public BusinessResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
