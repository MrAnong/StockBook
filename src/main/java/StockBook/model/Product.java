package StockBook.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Product {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String manufacturer;
    @Column
    private String Origin;
    @Column
    private String description;
    @Column
    private LocalDate expDate;
    @Column(columnDefinition = "float")
    private float costPrice;
    @Column
    private float vat;
    @Column
    private float unitPrice;
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column
    private long fkInventoryManager;
    @Column
    private long fkCategory;
    @Column
    private long fkSupplier;

    public Product() {
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //********** RELATIONSHIPS **********

    public long getFkInventoryManager() {
		return fkInventoryManager;
	}

	public void setFkInventoryManager(long fkInventoryManager) {
		this.fkInventoryManager = fkInventoryManager;
	}

	public long getFkCategory() {
		return fkCategory;
	}

	public void setFkCategory(long fkCategory) {
		this.fkCategory = fkCategory;
	}

	public long getFkSupplier() {
		return fkSupplier;
	}

	public void setFkSupplier(long fkSupplier) {
		this.fkSupplier = fkSupplier;
	}

	//a product can be added by one and only one user(inventory manager)
    @ManyToOne
    @JoinColumn(name = "addedBy", referencedColumnName = "id", nullable=false)
    @JsonBackReference
    private Users inventoryManager;

    //a product can belong to one and only one category
    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Product_Category productCategory;

    //a product can be supplied by one and only one supplier
    @ManyToOne
    @JoinColumn(name = "supplier", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Suppliers supplier;

    //a product can have several inventory records
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Inventory> inventoryList;

    //a product can be found in several stock request records
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Stock_Request> stockRequestsList;

    //********** FOREIGN KEY METHODS ***********


    public Users getInventoryManager() {
        return inventoryManager;
    }

    public void setInventoryManager(Users inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public Product_Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Product_Category productCategory) {
        this.productCategory = productCategory;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public List<Stock_Request> getStockRequestsList() {
        return stockRequestsList;
    }

    public void setStockRequestsList(List<Stock_Request> stockRequestsList) {
        this.stockRequestsList = stockRequestsList;
    }
}
