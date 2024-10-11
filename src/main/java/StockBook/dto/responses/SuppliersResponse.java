package StockBook.dto.responses;

import StockBook.model.Suppliers;

public class SuppliersResponse {

	private String message;
    private Suppliers supplier;

    public SuppliersResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }
}
