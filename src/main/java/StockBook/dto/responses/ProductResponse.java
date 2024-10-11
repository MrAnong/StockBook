package StockBook.dto.responses;

import StockBook.model.Product;

public class ProductResponse {

	private String message;
    private Product products;

    public ProductResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        this.products = product;
    }
}
