package StockBook.model;

import java.math.BigDecimal;
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
public class Expense {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private float amount;
    @Column
    private long fkInvoice;
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
    
    public long getFkInvoice() {
		return fkInvoice;
	}

	public void setFkInvoice(long fkInvoice) {
		this.fkInvoice = fkInvoice;
	}

    //********** RELATIONSHIPS **********

   

	//an expense record can belong to one and only one store
    @ManyToOne
    @JoinColumn(name = "store", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "expense-store")
    private Stores store;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference(value = "expense-invoice")
    private Invoice invoice;

    //********** FOREEIGN KEY METHODS **********


    public Stores getStore() {
        return store;
    }

    public void setStore(Stores store) {
        this.store = store;
    }

	public long getFkStore() {
		return fkStore;
	}

	public void setFkStore(long fkStore) {
		this.fkStore = fkStore;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
