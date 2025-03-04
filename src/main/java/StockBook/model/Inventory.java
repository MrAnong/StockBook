package StockBook.model;

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
public class Inventory {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int quantity;
    @Column
    private long fkProduct;
    @Column
    private long fkManager;

    public Inventory() {
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

    //********** RELATIONSHIPS **********

    public long getFkProduct() {
		return fkProduct;
	}

	public void setFkProduct(long fkProduct) {
		this.fkProduct = fkProduct;
	}

	public long getFkManager() {
		return fkManager;
	}

	public void setFkManager(long fkManager) {
		this.fkManager = fkManager;
	}

	//an inventory record can only be of a single product
//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
//    @JsonBackReference(value = "inventory-product")
//    private Product product;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference(value = "inventory-product")
    private Product product;
    
    //an inventory can be updated by a single manager
    @ManyToOne
    @JoinColumn(name = "stock_Manager", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "inventory-user")
    private Users stock_Manager;

    //********* FOREIGN KEY METHODS **********


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

	public Users getStock_Manager() {
		return stock_Manager;
	}

	public void setStock_Manager(Users stock_Manager) {
		this.stock_Manager = stock_Manager;
	}
    
    
}
