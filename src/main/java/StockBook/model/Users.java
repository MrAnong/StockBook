package StockBook.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Entity
public class Users {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String lastName;
    @Column(unique=true)
    private String email;
    @Column(unique=true)
    private String phoneNumber;
    @Column(unique=true)
    private String username;
    @Column
    private String location;
    @Column
    private String password;
    @Column
    private String status;
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column
    private long fkRole;
    @Column
    private long fkStore;

    public Users() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
 // Ensure password is not serialized
    public String getPassword() {
        return password;
    }
    
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //********** RELATIONSHIPS ************

    //a user can have one and only one role.
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id", nullable=false)
    @JsonBackReference(value = "user-role")
    private Role role;
    
  //a user account can only belong to oone store.
    @ManyToOne
    @JoinColumn(name = "store", referencedColumnName = "id", nullable=true)
    @JsonBackReference(value = "user-store")
    private Stores store;

    //one user(inventory manager) can add several products
    @OneToMany(mappedBy = "inventoryManager")
    @JsonManagedReference(value = "product-user")
    private List<Product> addedProducts;

    //one user(inventory manager) can create several stock requests
//    @OneToMany(mappedBy = "inventoryManager")
//    private List<Stock_Request> stockRequestsList;

    //one user(store manager) can add several product categories.
    @OneToMany(mappedBy = "storeManager")
    @JsonManagedReference(value = "productCategory-user")
    private List<Product_Category> productCategoriesList;

    //one user(store manager) can add several suppliers
    @OneToMany(mappedBy = "storeManager")
    @JsonManagedReference(value = "supplier-user")
    private List<Suppliers> suppliersList;

    //one user(business owner)can own several businesses
    @OneToMany(mappedBy = "businessOwner")
    @JsonManagedReference(value = "business-user")
    private List<Business> businesses;

    //a user(Teller) can generate several receipts
    @OneToMany(mappedBy = "teller")
    @JsonManagedReference(value = "receipt-user")
    private List<Receipt> receiptsList;

    //a user(stock manager) can generate several invoices
    @OneToMany(mappedBy = "stockManager")
    @JsonManagedReference(value = "generateInvoice-user")
    private List<Invoice> generatedInvoicesList;
    
    //a user(store manager) can validate several invoices
    @OneToMany(mappedBy = "storeManager")
    @JsonManagedReference(value = "validateInvoice-user")
    private List<Invoice> validatedInvoicesList;
    
  //a stock manager can create several inventories
    @OneToMany(mappedBy = "stock_Manager")
    @JsonManagedReference(value = "inventory-user")
    private List<Inventory> addedInventories;

    //*********** FOREIGN KEY METHODS **********


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Product> getAddedProducts() {
        return addedProducts;
    }

    public void setAddedProducts(List<Product> addedProducts) {
        this.addedProducts = addedProducts;
    }

//    public List<Stock_Request> getStockRequestsList() {
//        return stockRequestsList;
//    }
//
//    public void setStockRequestsList(List<Stock_Request> stockRequestsList) {
//        this.stockRequestsList = stockRequestsList;
//    }

    public List<Product_Category> getProductCategoriesList() {
        return productCategoriesList;
    }

    public void setProductCategoriesList(List<Product_Category> productCategoriesList) {
        this.productCategoriesList = productCategoriesList;
    }

    public List<Suppliers> getSuppliersList() {
        return suppliersList;
    }

    public void setSuppliersList(List<Suppliers> suppliersList) {
        this.suppliersList = suppliersList;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public List<Receipt> getReceiptsList() {
        return receiptsList;
    }

    public void setReceiptsList(List<Receipt> receiptsList) {
        this.receiptsList = receiptsList;
    }

    public List<Invoice> getGeneratedInvoicesList() {
        return generatedInvoicesList;
    }

    public void setGeneratedInvoicesList(List<Invoice> generatedInvoicesList) {
        this.generatedInvoicesList = generatedInvoicesList;
    }
    
    //*************************** Transient methods and attributes ***********

	public long getFkRole() {
		return fkRole;
	}

	public void setFkRole(long fkRole) {
		this.fkRole = fkRole;
	}

	public long getFkStore() {
		return fkStore;
	}

	public void setFkStore(long fkStore) {
		this.fkStore = fkStore;
	}

	public Stores getStore() {
		return store;
	}

	public void setStore(Stores store) {
		this.store = store;
	}

	public List<Invoice> getValidatedInvoicesList() {
		return validatedInvoicesList;
	}

	public void setValidatedInvoicesList(List<Invoice> validatedInvoicesList) {
		this.validatedInvoicesList = validatedInvoicesList;
	}

	public List<Inventory> getAddedInventories() {
		return addedInventories;
	}

	public void setAddedInventories(List<Inventory> addedInventories) {
		this.addedInventories = addedInventories;
	}
	
	
	//********** session **********
	
	// Add this method in your Users class
	public String generateJwtToken(String secretKey) {
	    // Convert LocalDateTime to Date
	    Date issuedAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	    Date expiration = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()); // 1 day expiration

	    return Jwts.builder()
	            .setSubject(this.username)
	            .claim("role", this.role.getName()) // Assuming Role has a getName() method
	            .setIssuedAt(issuedAt) // Use Date here
	            .setExpiration(expiration) // Use Date here
	            .signWith(SignatureAlgorithm.HS256, secretKey)
	            .compact();
	}
    
}
