package StockBook.dto.responses;

import StockBook.model.Inventory;

public class InventoryResponse {

	private String message;
    private Inventory inventory;

    public InventoryResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
