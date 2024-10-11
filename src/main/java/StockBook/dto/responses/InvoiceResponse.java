package StockBook.dto.responses;

import StockBook.model.Invoice;

public class InvoiceResponse {

	private String message;
    private Invoice invoice;

    public InvoiceResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
