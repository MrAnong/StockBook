package StockBook.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Stores {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String town;
    @Column
    private String location;
    @Column
    private String status;
    @Column
    private long fkBusiness;

    public Stores() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public long getFkBusiness() {
		return fkBusiness;
	}

	public void setFkBusiness(long fkBusiness) {
		this.fkBusiness = fkBusiness;
	}


    //********** RELATIONSHIPS ***********



	//a store can belong to one and only one business
    @ManyToOne
    @JoinColumn(name = "business", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Business business;

    //a store can have several income records
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private List<Income> incomeList;
    
    //a store can have several receipts
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private List<Receipt> receiptList;
    
    //a store can have several income records
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private List<Invoice> invoiceList;

    //a store can have several expense records
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private List<Expense> expenseList;

    //********** FOREIGN KEY METHODS *************

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

	public List<Receipt> getReceiptList() {
		return receiptList;
	}

	public void setReceiptList(List<Receipt> receiptList) {
		this.receiptList = receiptList;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
    
    
}
