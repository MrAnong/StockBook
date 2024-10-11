package StockBook.dto.responses;

import StockBook.model.ReceiptItem;


public class ReceiptItemResponse {

	private String message;
    private ReceiptItem receiptItems;

    public ReceiptItemResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReceiptItem getReceiptItems() {
        return receiptItems;
    }

    public void setReceiptItems(ReceiptItem receiptItems) {
        this.receiptItems = receiptItems;
    }
}
