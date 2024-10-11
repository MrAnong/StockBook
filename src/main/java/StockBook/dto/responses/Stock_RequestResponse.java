package StockBook.dto.responses;

import StockBook.model.Stock_Request;

public class Stock_RequestResponse {

	private String message;
    private Stock_Request stock_requests;

    public Stock_RequestResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Stock_Request getStock_Requests() {
        return stock_requests;
    }

    public void setStock_Requests(Stock_Request stock_requests) {
        this.stock_requests = stock_requests;
    }
}
