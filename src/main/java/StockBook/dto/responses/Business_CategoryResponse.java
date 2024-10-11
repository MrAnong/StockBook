package StockBook.dto.responses;

import StockBook.model.Business_Category;

public class Business_CategoryResponse {


	    private String message;
	    private Business_Category category;


	    public Business_CategoryResponse() {
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public Business_Category getCategory() {
	        return category;
	    }

	    public void setCategory(Business_Category category) {
	        this.category = category;
	    }
	}

