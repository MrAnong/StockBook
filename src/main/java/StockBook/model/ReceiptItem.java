package StockBook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class ReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long productId;

    @Column
    private int quantity;

    @Column
    private float amount;

    @ManyToOne
    @JoinColumn(name = "receipt_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Receipt receipt;

    // Default constructor
    public ReceiptItem() {
    }

    // Parameterized constructor
    public ReceiptItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    // toString method for debugging and logging purposes
    @Override
    public String toString() {
        return "ReceiptItem{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", receipt=" + (receipt != null ? receipt.getId() : "null") +
                '}';
    }
}