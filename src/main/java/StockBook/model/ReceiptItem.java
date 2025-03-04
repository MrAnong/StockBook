package StockBook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class ReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long fkProduct;

    @Column
    private int quantity;

    @Column
    private float amount;
    
    @Column
    private long fkReceipt;

    @ManyToOne
    @JoinColumn(name = "receipt_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "item-receipt")
    private Receipt receipt;
    
  //a stock request record can only contain a product
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "receiptItem-product")
    private Product product;

    // Default constructor
    public ReceiptItem() {
    }

    // Parameterized constructor
    public ReceiptItem(int fkProduct, int quantity) {
        this.fkProduct = fkProduct;
        this.quantity = quantity;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(long fkProduct) {
        this.fkProduct = fkProduct;
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
    
    public void setFkReceipt(long fkReceipt) {
    	this.fkReceipt = fkReceipt;
    }
    
    public long getFkReceipt() {
    	return fkReceipt;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// toString method for debugging and logging purposes
    @Override
    public String toString() {
        return "ReceiptItem{" +
                "id=" + id +
                ", fkProduct=" + fkProduct +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", receipt=" + (receipt != null ? receipt.getId() : "null") +
                '}';
    }
}