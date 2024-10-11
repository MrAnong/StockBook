package StockBook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Stock_Request {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int quantity;
    @Column
    private float cost;
    @Column
    private String status;
    @Column
    private long fkProduct;
    @Column
    private long fkInvoice;

    public Stock_Request() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    //********** RELATIONSHIPS ***************

    public long getFkProduct() {
		return fkProduct;
	}

	public void setFkProduct(long fkProduct) {
		this.fkProduct = fkProduct;
	}

	public long getFkInvoice() {
		return fkInvoice;
	}

	public void setFkInvoice(long fkInvoice) {
		this.fkInvoice = fkInvoice;
	}

	public void setStatus(String status) {
        this.status = status;
    }

    //one stock request can be created by one and only one user(inventory manager)
//    @ManyToOne
//    @JoinColumn(name = "createdBy", referencedColumnName = "id", nullable=false)
//    private Users inventoryManager;

    //a stock request record can only contain a product
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Product product;
    
    //one stock request can belong to only one invoice
    @ManyToOne
    @JoinColumn(name = "invoiceId", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Invoice invoice;

    //********** FOREIGN KEY METHODS **********


//    public Users getInventoryManager() {
//        return inventoryManager;
//    }
//
//    public void setInventoryManager(Users inventoryManager) {
//        this.inventoryManager = inventoryManager;
//    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
    
    
}
