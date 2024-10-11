package StockBook.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column
    private float total;
    @Column
    private long fkTeller;
    @Column
    private long fkStore;
    
    @Transient
    private List<ReceiptItem> receiptItemList;

    // Default constructor
    public Receipt() {
    }

    @ManyToOne
    @JoinColumn(name = "teller_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Users teller;
    
    @ManyToOne
    @JoinColumn(name = "store", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Stores store;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ReceiptItem> receiptItems = new ArrayList<>();

    // Getter and Setter for id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Getter and Setter for createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getter and Setter for total
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    // Getter and Setter for teller
    public Users getTeller() {
        return teller;
    }

    public void setTeller(Users teller) {
        this.teller = teller;
    }

    public Stores getStore() {
		return store;
	}

	public void setStore(Stores store) {
		this.store = store;
	}

	// Getter and Setter for receiptItems
    public List<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public void setReceiptItems(List<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
        updateTotal(); // Update total when setting new items
    }
    
    

    public List<ReceiptItem> getReceiptItemList() {
		return receiptItemList;
	}

	public void setReceiptItemList(List<ReceiptItem> receiptItemList) {
		this.receiptItemList = receiptItemList;
	}

	// Helper method to add a single ReceiptItem
    public void addReceiptItem(ReceiptItem item) {
        receiptItems.add(item);
        item.setReceipt(this); // Set the receipt for the item (bidirectional)
        updateTotal(); // Recalculate the total after adding an item
    }

    // Helper method to remove a single ReceiptItem
    public void removeReceiptItem(ReceiptItem item) {
        receiptItems.remove(item);
        item.setReceipt(null); // Remove the receipt reference from the item
        updateTotal(); // Recalculate the total after removing an item
    }

    // Private method to update the total
    private void updateTotal() {
        this.total = receiptItems.stream()
                .map(ReceiptItem::getAmount)
                .reduce(0f, Float::sum);
    }

    // toString method for debugging and logging purposes
    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", teller=" + (teller != null ? teller.getId() : "null") +
                ", receiptItems=" + receiptItems.size() +
                ", total=" + total +
                '}';
    }
}