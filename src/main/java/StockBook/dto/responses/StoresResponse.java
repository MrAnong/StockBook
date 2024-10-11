package StockBook.dto.responses;

import StockBook.model.Stores;

public class StoresResponse {

	private String message;
    private Stores store;

    public StoresResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Stores getStore() {
        return store;
    }

    public void setStore(Stores store) {
        this.store = store;
    }
}
