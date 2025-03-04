package StockBook.model;

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
public class Suppliers {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String fullName;
    @Column
    private String company;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String location;
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column
    private long fkStoreManager;

    public Suppliers() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //********** RELATIONSHIPS ***************

    public long getFkStoreManager() {
		return fkStoreManager;
	}

	public void setFkStoreManager(long fkStoreManager) {
		this.fkStoreManager = fkStoreManager;
	}

	//one supplier can be added by one and only one user(store manager)
    @ManyToOne
    @JoinColumn(name = "addedBy", referencedColumnName = "id", nullable=false)
    @JsonBackReference(value = "supplier-user")
    private Users storeManager;

    //a supplier can supply several products
    @OneToMany(mappedBy = "supplier")
    @JsonManagedReference(value = "product-supplier")
    private List<Product> productsSuppliedList;

    //************ FOREIGN KEY METHODS ***********


    public Users getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(Users storeManager) {
        this.storeManager = storeManager;
    }

    public List<Product> getProductsSuppliedList() {
        return productsSuppliedList;
    }

    public void setProductsSuppliedList(List<Product> productsSuppliedList) {
        this.productsSuppliedList = productsSuppliedList;
    }
}
