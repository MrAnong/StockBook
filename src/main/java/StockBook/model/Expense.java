package StockBook.model;

import java.math.BigDecimal;
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
public class Expense {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private BigDecimal amount;
    @Column
    private long invoice_Id;
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column
    private long fkStore;

    public Expense() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //********** RELATIONSHIPS **********

    //an expense record can belong to one and only one store
    @ManyToOne
    @JoinColumn(name = "store", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Stores store;

    //********** FOREEIGN KEY METHODS **********


    public Stores getStore() {
        return store;
    }

    public void setStore(Stores store) {
        this.store = store;
    }

	public long getInvoice_Id() {
		return invoice_Id;
	}

	public void setInvoice_Id(long invoice_Id) {
		this.invoice_Id = invoice_Id;
	}

	public long getFkStore() {
		return fkStore;
	}

	public void setFkStore(long fkStore) {
		this.fkStore = fkStore;
	}
}
