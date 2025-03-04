package StockBook.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Invoice {

	 	@Id
	    @Column
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	 	@Transient
	 	private List<Stock_Request> stockRequests;
	    @Column
	    private String status;
	    @Column
	    private float amount;
	    @Column
	    private long fkStoreManager;
	    @Column
	    private long fkInventoryManager;
	    @Column
	    private long fkStore;

	    public Invoice() {
	    }

	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }
	    
	    

	    public List<Stock_Request> getStockRequests() {
			return stockRequests;
		}

		public void setStockRequests(List<Stock_Request> stockRequests) {
			this.stockRequests = stockRequests;
		}


		public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public long getFkStoreManager() {
			return fkStoreManager;
		}

		public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

		public void setFkStoreManager(long fkStoreManager) {
			this.fkStoreManager = fkStoreManager;
		}

		public long getFkInventoryManager() {
			return fkInventoryManager;
		}

		public void setFkInventoryManager(long fkStockManager) {
			this.fkInventoryManager = fkStockManager;
		}

		public long getFkStore() {
			return fkStore;
		}

		public void setFkStore(long fkStore) {
			this.fkStore = fkStore;
		}
		
		 //********** RELATIONSHIPS *********

		//an invoice can be generated by one and only one user(stock manager)
	    @ManyToOne
	    @JoinColumn(name = "generatedBy", referencedColumnName = "id", nullable = false)
	    @JsonBackReference(value = "generateInvoice-user")
	    private Users stockManager;
	    
	    //an invoice can be validated by one and only one user(stock manager)
	    @ManyToOne
	    @JoinColumn(name = "validatedBy", referencedColumnName = "id", nullable = true)
	    @JsonBackReference(value = "validateInvoice-user")
	    private Users storeManager;
	    
	    //an invoice can contain several stock requests
	    @OneToMany(mappedBy = "invoice")
	    @JsonManagedReference(value = "stockRequest-invoice")
	    private List<Stock_Request> stockRequestsList;
	    
	    @ManyToOne
	    @JoinColumn(name = "store", referencedColumnName = "id", nullable = false)
	    @JsonBackReference(value = "invoice-store")
	    private Stores store;
	    
	    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonBackReference(value = "expense-invoice")
	    private Expense expense;

	    //********** FOREIGN KEY METHODS ***********


	    public Users getStockManager() {
	        return stockManager;
	    }

	    public void setStockManager(Users stockManager) {
	        this.stockManager = stockManager;
	    }
	    
		public Users getStoreManager() {
			return stockManager;
		}

		public void setStoreManager(Users stockManager) {
			this.stockManager = stockManager;
		}

		public List<Stock_Request> getStockRequestsList() {
			return stockRequestsList;
		}

		public void setStockRequestsList(List<Stock_Request> stockRequestsList) {
			this.stockRequestsList = stockRequestsList;
		}
		
		public Stores getStore() {
			return store;
		}

		public void setStore(Stores store) {
			this.store = store;
		}

		public Expense getExpense() {
			return expense;
		}

		public void setExpense(Expense expense) {
			this.expense = expense;
		}
}
