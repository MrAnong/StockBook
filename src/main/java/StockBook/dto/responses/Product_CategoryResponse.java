package StockBook.dto.responses;

import StockBook.model.Product_Category;


public class Product_CategoryResponse {

	private String message;
    private Product_Category category;

    public Product_CategoryResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product_Category getCategory() {
        return category;
    }

    public void setCategory(Product_Category category) {
        this.category = category;
    }
}
