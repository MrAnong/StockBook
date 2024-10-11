package StockBook.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Income {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private float amount;
    @Column
    private long receipt_id;
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
    @JsonBackReference
    private Stores store;

    //********** FREIGN KEY METHODS *********


    public Stores getStore() {
        return store;
    }

    public void setStore(Stores store) {
        this.store = store;
    }

	public long getReceipt_id() {
		return receipt_id;
	}

	public void setReceipt_id(long receipt_id) {
		this.receipt_id = receipt_id;
	}

	public long getFkStore() {
		return fkStore;
	}

	public void setFkStore(long fkStore) {
		this.fkStore = fkStore;
	}
}
