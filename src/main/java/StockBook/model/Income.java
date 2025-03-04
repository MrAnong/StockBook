package StockBook.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Income {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private float amount;
    @Column
    private long fkReceipt;
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column
    private long fkStore;

    public Income() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //********** RELATIONSHIPS **********

    //an income record can belong to one and only one store
    @ManyToOne
    @JoinColumn(name = "store", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "income-store")
    private Stores store;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference(value = "income-receipt")
    private Receipt receipt;

    //********** FREIGN KEY METHODS *********


    public Stores getStore() {
        return store;
    }

    public void setStore(Stores store) {
        this.store = store;
    }

	public long getFkReceipt() {
		return fkReceipt;
	}

	public void setFkReceipt(long fkReceipt) {
		this.fkReceipt = fkReceipt;
	}

	public long getFkStore() {
		return fkStore;
	}

	public void setFkStore(long fkStore) {
		this.fkStore = fkStore;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
}
