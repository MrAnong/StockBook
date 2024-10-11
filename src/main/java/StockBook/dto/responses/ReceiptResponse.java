package StockBook.dto.responses;

import StockBook.model.Receipt;

public class ReceiptResponse {

	 private String messsage;
	    private Receipt receipts;

	    public ReceiptResponse() {
	    }

	    public String getMesssage() {
	        return messsage;
	    }

	    public void setMessage(String messsage) {
	        this.messsage = messsage;
	    }

	    public Receipt getReceipts() {
	        return receipts;
	    }

	    public void setReceipts(Receipt receipts) {
	        this.receipts = receipts;
	    }
}
